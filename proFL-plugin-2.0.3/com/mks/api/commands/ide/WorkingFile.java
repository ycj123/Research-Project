// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands.ide;

import java.util.ArrayList;
import com.mks.api.response.modifiable.ModifiableField;
import com.mks.api.response.impl.SimpleResponseFactory;
import com.mks.api.response.Field;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import com.mks.api.response.ItemNotFoundException;
import com.mks.api.response.InvalidItemException;
import com.mks.api.response.APIError;
import java.util.HashMap;
import java.util.Date;
import com.mks.api.response.WorkItem;
import com.mks.api.response.APIException;
import java.io.File;
import java.util.Map;
import com.mks.api.response.Item;

public final class WorkingFile implements Item
{
    public static final String INVALID_MOVE_TO_CP_OPERATION = "INVALID_MOVE_TO_CP_OPERATION";
    private long ordinal;
    private boolean valid;
    private static final String WF_FIELD_EXCLUSIVE_LOCKED_BY_OTHER = "exclusiveLockedByOther";
    private static final String WF_FIELD_LOCKED_BY_OTHER = "lockedByOther";
    private static final String WF_FIELD_LOCKED_BY_ME = "lockedByMe";
    private static final String WF_FIELD_OUT_OF_DATE = "outOfDate";
    private static final String WF_FIELD_MODIFIED = "modified";
    private static final String WF_FIELD_INCOMING = "incoming";
    private static final String WF_FIELD_MOVED = "moved";
    private static final String WF_FIELD_DROPPED = "dropped";
    private static final String WF_FIELD_ADDED = "added";
    private static final String WF_FIELD_FORMER_MEMBER = "formerMember";
    private static final String WF_FIELD_MEMBER = "member";
    private static final String WF_FIELD_CONTROLLED = "controlled";
    private static final String WF_FIELD_IN_SANDBOX_DIR = "inSandboxDir";
    private static final String WF_FIELD_WORKING_DELTA = "workingDelta";
    private static final String WF_FIELD_WORKING_CPID = "workingCpid";
    private static final String WF_FIELD_WORKING_REV = "workingRev";
    private static final String WF_FIELD_MEMBER_REV = "memberRev";
    private static final String WF_FIELD_SANDBOX = "sandbox";
    private static final String WF_FIELD_IS_SHARED = "shared";
    private static final String WF_FIELD_MEMBER_NAME = "memberName";
    private static final String WF_FIELD_IS_PENDING = "pending";
    private static final String WF_FIELD_IS_PENDING_ADD = "pendingAdd";
    private static final String WF_FIELD_EXCLUSIVE = "exclusiveLockedByMe";
    private static final String WF_FIELD_SUBMITTABLE = "submittable";
    private static final String WF_FIELD_INVALID = "invalid";
    private static final String WF_FIELD_ORDINAL = "ordinal";
    private Map fields;
    private File file;
    private File workingRoot;
    private APIException exception;
    
    WorkingFile(final File file, final String sandbox, final File root, final WorkItem viewsandboxWorkItem, final Date timestamp, final String userId, final long ordinal) {
        this(file, sandbox, root, false, timestamp, ordinal);
        this.generateFromWorkItem(viewsandboxWorkItem, userId);
    }
    
    WorkingFile(final File file, final APIException ex, final Date timestamp, final long ordinal) {
        this(file, null, null, false, ex, timestamp, ordinal);
    }
    
    WorkingFile(final File file, final String sandbox, final File root, final boolean controlled, final Date timestamp, final long ordinal) {
        this(file, sandbox, root, controlled, null, timestamp, ordinal);
    }
    
    private WorkingFile(final File file, final String sandbox, final File root, final boolean controlled, APIException ex, final Date timestamp, final long ordinal) {
        (this.fields = new HashMap(24)).put("memberName", null);
        this.fields.put("sandbox", null);
        this.fields.put("inSandboxDir", Boolean.FALSE);
        this.fields.put("controlled", Boolean.FALSE);
        this.fields.put("member", Boolean.FALSE);
        this.fields.put("formerMember", Boolean.FALSE);
        this.fields.put("added", Boolean.FALSE);
        this.fields.put("dropped", Boolean.FALSE);
        this.fields.put("moved", Boolean.FALSE);
        this.fields.put("modified", Boolean.FALSE);
        this.fields.put("outOfDate", Boolean.FALSE);
        this.fields.put("incoming", Boolean.FALSE);
        this.fields.put("lockedByMe", Boolean.FALSE);
        this.fields.put("lockedByOther", Boolean.FALSE);
        this.fields.put("exclusiveLockedByOther", Boolean.FALSE);
        this.fields.put("pending", Boolean.FALSE);
        this.fields.put("pendingAdd", Boolean.FALSE);
        this.fields.put("exclusiveLockedByMe", Boolean.FALSE);
        this.fields.put("workingDelta", Boolean.FALSE);
        this.fields.put("workingCpid", null);
        this.fields.put("workingRev", null);
        this.fields.put("memberRev", null);
        this.fields.put("shared", Boolean.FALSE);
        this.exception = null;
        if (timestamp == null) {
            throw new APIError("Assertion: Working File missing timestamp");
        }
        this.file = file;
        this.workingRoot = root;
        this.ordinal = ordinal;
        this.fields.put("memberName", file);
        this.fields.put("sandbox", sandbox);
        this.fields.put("inSandboxDir", (sandbox == null) ? Boolean.FALSE : Boolean.TRUE);
        this.fields.put("controlled", controlled ? Boolean.TRUE : Boolean.FALSE);
        if (ex == null) {
            if (file == null || file.isDirectory()) {
                ex = new InvalidItemException();
            }
            if (!file.exists()) {
                ex = new ItemNotFoundException();
            }
        }
        this.setAPIException(ex);
    }
    
    private File constructMemberFile(final String sandbox, final String relativePath) {
        final File sandboxDir = new File(sandbox).getParentFile();
        return new File(sandboxDir, relativePath);
    }
    
    private void generateFromWorkItem(final WorkItem wi, final String userId) {
        final APIException apix = wi.getAPIException();
        if (apix != null) {
            final String exceptionName = apix.getField("exception-name").getString();
            if (apix instanceof ItemNotFoundException) {
                if (!"si.MemberNotFound".equals(exceptionName)) {
                    if ("si.NoSuchSubproject".equals(exceptionName)) {
                        this.fields.put("formerMember", Boolean.TRUE);
                    }
                    else {
                        this.setAPIException(apix);
                    }
                }
                return;
            }
            this.setAPIException(apix);
        }
        else {
            if (this.getAPIException() instanceof ItemNotFoundException) {
                this.setAPIException(null);
            }
            this.fields.put("controlled", Boolean.TRUE);
            this.fields.put("inSandboxDir", Boolean.TRUE);
            if ("si.Sandbox".equals(wi.getModelType()) || "si.Project".equals(wi.getModelType())) {
                this.fields.put("memberName", this.file);
                this.fields.put("sandbox", this.file.getAbsolutePath());
                final String type = wi.getField("type").getValueAsString();
                final boolean shared = type.indexOf("shared") != -1;
                this.fields.put("shared", new Boolean(shared));
                return;
            }
            final String name = wi.getField("name").getString();
            this.file = new File(name);
            final String sandbox = wi.getField("canonicalSandbox").getString();
            final String relativeMemberName = wi.getField("canonicalMember").getString();
            final File memberFile = this.constructMemberFile(sandbox, relativeMemberName);
            this.fields.put("memberName", memberFile);
            this.fields.put("sandbox", sandbox);
            if ("si.FormerMember".equals(wi.getModelType())) {
                this.fields.put("formerMember", Boolean.TRUE);
                return;
            }
            String workingCpid = null;
            try {
                final Item item = wi.getField("workingcpid").getItem();
                if (item != null) {
                    workingCpid = item.getId();
                }
            }
            catch (NoSuchElementException ex) {}
            this.fields.put("workingCpid", workingCpid);
            if (!this.file.equals(memberFile)) {
                this.fields.put("moved", Boolean.TRUE);
            }
            final String type2 = wi.getField("type").getString();
            if ("si.DestinedMember".equals(wi.getModelType())) {
                if ("deferred-add".equals(type2)) {
                    this.fields.put("added", Boolean.TRUE);
                    return;
                }
                if ("deferred-move-to".equals(type2)) {
                    return;
                }
            }
            if (type2.indexOf("pending-add") != -1) {
                this.fields.put("pendingAdd", Boolean.TRUE);
            }
            this.fields.put("submittable", wi.getField("deferred").getBoolean());
            this.fields.put("member", Boolean.TRUE);
            if ("si.DoomedMember".equals(wi.getModelType())) {
                this.fields.put("dropped", Boolean.TRUE);
            }
            try {
                String memberRev = null;
                Item item = wi.getField("memberrev").getItem();
                if (item != null) {
                    memberRev = item.getId();
                }
                this.fields.put("memberRev", memberRev);
                String workingRev = null;
                item = wi.getField("workingrev").getItem();
                if (item != null) {
                    workingRev = item.getId();
                }
                this.fields.put("workingRev", workingRev);
                item = wi.getField("wfdelta").getItem();
                boolean wfDelta = false;
                boolean wfDeltaSizeChanged = false;
                boolean noWorkingFile = false;
                if (item != null) {
                    final Boolean wfDeltaObj = item.getField("isDelta").getBoolean();
                    if (wfDeltaObj != null) {
                        wfDelta = wfDeltaObj;
                    }
                    final Boolean noWfObj = item.getField("noWorkingFile").getBoolean();
                    if (noWfObj != null) {
                        noWorkingFile = noWfObj;
                    }
                    final Long currentSize = item.getField("workingFileSize").getLong();
                    if (currentSize != null) {
                        final Long cachedSize = item.getField("cachedFileSize").getLong();
                        wfDeltaSizeChanged = !currentSize.equals(cachedSize);
                    }
                }
                item = wi.getField("revsyncdelta").getItem();
                boolean revSyncDelta = false;
                boolean isWorkingRevUnknown = false;
                if (item != null) {
                    final Boolean revSyncDeltaObj = item.getField("isDelta").getBoolean();
                    if (revSyncDeltaObj != null) {
                        revSyncDelta = revSyncDeltaObj;
                    }
                    final Boolean isWorkingRevUnknownObj = item.getField("isWorkingRevUnknown").getBoolean();
                    if (isWorkingRevUnknownObj != null) {
                        isWorkingRevUnknown = isWorkingRevUnknownObj;
                    }
                    final Boolean isWorkingRevPendingObj = item.getField("isWorkingRevPending").getBoolean();
                    if (isWorkingRevPendingObj != null) {
                        this.fields.put("pending", isWorkingRevPendingObj);
                    }
                }
                if (wfDelta && !noWorkingFile) {
                    this.fields.put("workingDelta", Boolean.TRUE);
                    if (wfDeltaSizeChanged) {
                        this.markModified();
                    }
                }
                if (noWorkingFile && isWorkingRevUnknown) {
                    this.fields.put("incoming", Boolean.TRUE);
                }
                if (revSyncDelta) {
                    this.fields.put("outOfDate", Boolean.TRUE);
                }
                final Boolean workingRevLockedByMe = wi.getField("workingrevlockedbyme").getBoolean();
                if (workingRevLockedByMe != null) {
                    this.fields.put("lockedByMe", workingRevLockedByMe);
                }
                final List lockRecords = wi.getField("lockrecord").getList();
                if (lockRecords != null) {
                    for (final Item lockRecord : lockRecords) {
                        final Item revision = lockRecord.getField("revision").getItem();
                        final Item user = lockRecord.getField("locker").getItem();
                        final String lockType = lockRecord.getField("locktype").getString();
                        if (user != null && revision != null && workingRev != null && workingRev.equals(revision.getId())) {
                            if (user.getId().equals(userId)) {
                                if (!"exclusive".equals(lockType)) {
                                    continue;
                                }
                                this.fields.put("exclusiveLockedByMe", Boolean.TRUE);
                            }
                            else {
                                this.fields.put("lockedByOther", Boolean.TRUE);
                                if (!"exclusive".equals(lockType)) {
                                    continue;
                                }
                                this.fields.put("exclusiveLockedByOther", Boolean.TRUE);
                            }
                        }
                    }
                }
            }
            catch (NoSuchElementException ex2) {}
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WorkingFile)) {
            return false;
        }
        final WorkingFile wf = (WorkingFile)obj;
        if (this == wf) {
            return true;
        }
        if ((this.file == null && wf.file != null) || !this.file.equals(wf.file)) {
            return false;
        }
        if (this.fields.size() != wf.fields.size()) {
            return false;
        }
        for (final Object key : this.fields.keySet()) {
            final Object thisValue = this.fields.get(key);
            final Object wfValue = wf.fields.get(key);
            if ((thisValue == null && wfValue != null) || (thisValue != null && !thisValue.equals(wfValue))) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isInvalid() {
        return !this.valid;
    }
    
    void invalidate() {
        this.valid = false;
    }
    
    public boolean isInSandboxDir() {
        return this.fields.get("inSandboxDir");
    }
    
    public boolean isControlled() {
        return this.fields.get("controlled");
    }
    
    public boolean isMember() {
        return this.fields.get("member");
    }
    
    public boolean isFormerMember() {
        return this.fields.get("formerMember");
    }
    
    public boolean isAdded() {
        return this.fields.get("added");
    }
    
    public boolean isDropped() {
        return this.fields.get("dropped");
    }
    
    public boolean isMoved() {
        return this.fields.get("moved");
    }
    
    public boolean isDeferred() {
        return this.getWorkingCpid() != null || this.isAdded() || this.isDropped() || this.isMoved();
    }
    
    public boolean hasWorkingDelta() {
        return this.fields.get("workingDelta");
    }
    
    void markModified() {
        this.fields.put("modified", Boolean.TRUE);
    }
    
    public boolean isModified() {
        return this.fields.get("modified");
    }
    
    public boolean isOutOfDate() {
        return this.fields.get("outOfDate");
    }
    
    public boolean isIncoming() {
        return this.fields.get("incoming");
    }
    
    public boolean isLockedByMe() {
        return this.fields.get("lockedByMe");
    }
    
    public boolean isLockedByOther() {
        return this.fields.get("lockedByOther");
    }
    
    public boolean isExclusiveLockByOther() {
        return this.fields.get("exclusiveLockedByOther");
    }
    
    public boolean isExclusiveLockByMe() {
        return this.fields.get("exclusiveLockedByMe");
    }
    
    public boolean isPending() {
        return this.fields.get("pending");
    }
    
    public boolean isShared() {
        return this.fields.get("shared");
    }
    
    public boolean isPendingAdd() {
        return this.fields.get("pendingAdd");
    }
    
    public File getMemberName() {
        return this.fields.get("memberName");
    }
    
    public String getMemberRev() {
        return this.fields.get("memberRev");
    }
    
    public File getFile() {
        return this.file;
    }
    
    public String getName() {
        return this.file.getAbsolutePath();
    }
    
    public String getSandbox() {
        return this.fields.get("sandbox");
    }
    
    public String getWorkingCpid() {
        return this.fields.get("workingCpid");
    }
    
    public String getWorkingRev() {
        return this.fields.get("workingRev");
    }
    
    public File getWorkingRoot() {
        return this.workingRoot;
    }
    
    public String toString() {
        return this.getName();
    }
    
    public String statusString() {
        String string = "Working File: " + this.getName() + "\n" + " Member Name: " + this.getMemberName() + "\n" + " Sandbox: " + this.getSandbox() + "\n" + " Working Root: " + this.getWorkingRoot() + "\n" + " Working CPID: " + this.getWorkingCpid() + "\n" + " Member Rev: " + this.getMemberRev() + "\n" + " Working Rev: " + this.getWorkingRev() + "\n" + " Invalid: " + this.isInvalid() + "\n" + " In Sandbox Dir: " + this.isInSandboxDir() + "\n" + " isControlled: " + this.isControlled() + "\n" + " member: " + this.isMember() + "\n" + " former Member: " + this.isFormerMember() + "\n" + " added: " + this.isAdded() + "\n" + " dropped: " + this.isDropped() + "\n" + " moved: " + this.isMoved() + "\n" + " has WF Delta: " + this.hasWorkingDelta() + "\n" + " modified: " + this.isModified() + "\n" + " incoming: " + this.isIncoming() + "\n" + " outOfDate: " + this.isOutOfDate() + "\n" + " locked by me: " + this.isLockedByMe() + "\n" + " locked by other: " + this.isLockedByOther() + "\n" + " deferred: " + this.isDeferred() + "\n" + " exclusive by other: " + this.isExclusiveLockByOther() + "\n" + " exclusive by me: " + this.isExclusiveLockByMe() + "\n" + " pending: " + this.isPending() + "\n" + " pending Add: " + this.isPendingAdd() + "\n" + " ordinal: " + this.getOrdinal() + "\n";
        if (this.exception != null) {
            string = string + " exception: " + this.exception.getExceptionId() + "\n";
        }
        return string;
    }
    
    private void setAPIException(final APIException exception) {
        this.exception = exception;
        this.valid = (exception == null);
    }
    
    public APIException getAPIException() {
        return this.exception;
    }
    
    public long getOrdinal() {
        return this.ordinal;
    }
    
    public String getContext() {
        return this.getSandbox();
    }
    
    public String getContext(final String key) {
        return null;
    }
    
    public Enumeration getContextKeys() {
        return new Enumeration() {
            public boolean hasMoreElements() {
                return false;
            }
            
            public Object nextElement() {
                return null;
            }
        };
    }
    
    public String getDisplayId() {
        return null;
    }
    
    public String getId() {
        String id = this.getName();
        if (WorkingFileFactory.isWin32()) {
            id = id.toLowerCase();
        }
        return id;
    }
    
    public String getModelType() {
        return "si.WorkingFile";
    }
    
    public boolean contains(final String id) {
        return this.fields.containsKey(id);
    }
    
    public Field getField(final String id) {
        Object fieldValue = null;
        if ("invalid".equals(id)) {
            fieldValue = (this.isInvalid() ? Boolean.TRUE : Boolean.FALSE);
        }
        else if ("ordinal".equals(id)) {
            fieldValue = new Long(this.ordinal);
        }
        else {
            if (!this.contains(id)) {
                throw new NoSuchElementException();
            }
            fieldValue = this.fields.get(id);
        }
        final SimpleResponseFactory factory = SimpleResponseFactory.getResponseFactory();
        final ModifiableField field = factory.createField(id);
        field.setValue(fieldValue);
        return field;
    }
    
    public int getFieldListSize() {
        return this.fields.size() + 2;
    }
    
    public Iterator getFields() {
        final List itemFields = new ArrayList(this.getFieldListSize());
        final Iterator ids = this.fields.keySet().iterator();
        while (ids.hasNext()) {
            itemFields.add(this.getField(ids.next()));
        }
        itemFields.add(this.getField("invalid"));
        itemFields.add(this.getField("ordinal"));
        return itemFields.iterator();
    }
}
