// 
// Decompiled by Procyon v0.5.36
// 

package groovy.ui.text;

import javax.swing.undo.CompoundEdit;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import java.beans.PropertyChangeListener;
import javax.swing.undo.UndoableEdit;
import javax.swing.event.SwingPropertyChangeSupport;
import javax.swing.undo.UndoManager;

public class TextUndoManager extends UndoManager
{
    private SwingPropertyChangeSupport propChangeSupport;
    private StructuredEdit compoundEdit;
    private long firstModified;
    private UndoableEdit modificationMarker;
    
    public TextUndoManager() {
        this.propChangeSupport = new SwingPropertyChangeSupport(this);
        this.compoundEdit = new StructuredEdit();
        this.modificationMarker = this.editToBeUndone();
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        this.propChangeSupport.addPropertyChangeListener(pcl);
    }
    
    @Override
    public void die() {
        final boolean undoable = this.canUndo();
        super.die();
        this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
    }
    
    @Override
    public void discardAllEdits() {
        final boolean undoable = this.canUndo();
        final boolean redoable = this.canRedo();
        final boolean changed = this.hasChanged();
        super.discardAllEdits();
        this.modificationMarker = this.editToBeUndone();
        this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
        this.firePropertyChangeEvent("Undo", redoable, this.canRedo());
    }
    
    protected void firePropertyChangeEvent(final String name, final boolean oldValue, final boolean newValue) {
        this.propChangeSupport.firePropertyChange(name, oldValue, newValue);
    }
    
    public boolean hasChanged() {
        return this.modificationMarker != this.editToBeUndone();
    }
    
    @Override
    public void redo() throws CannotRedoException {
        this.compoundEdit.end();
        if (this.firstModified == 0L) {
            this.firstModified = ((StructuredEdit)this.editToBeRedone()).editedTime();
        }
        final boolean undoable = this.canUndo();
        final boolean changed = this.hasChanged();
        super.redo();
        this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
    }
    
    @Override
    protected void redoTo(final UndoableEdit edit) {
        this.compoundEdit.end();
        if (this.firstModified == 0L) {
            this.firstModified = ((StructuredEdit)this.editToBeRedone()).editedTime();
        }
        final boolean undoable = this.canUndo();
        final boolean changed = this.hasChanged();
        super.redoTo(edit);
        this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        this.propChangeSupport.removePropertyChangeListener(pcl);
    }
    
    public void reset() {
        final boolean changed = this.modificationMarker != this.editToBeUndone();
        if (changed) {
            this.modificationMarker = this.editToBeUndone();
        }
    }
    
    @Override
    protected void trimEdits(final int from, final int to) {
        final boolean undoable = this.canUndo();
        final boolean redoable = this.canRedo();
        final boolean changed = this.hasChanged();
        super.trimEdits(from, to);
        this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
        this.firePropertyChangeEvent("Redo", redoable, this.canRedo());
    }
    
    @Override
    public void undo() throws CannotUndoException {
        this.compoundEdit.end();
        final UndoableEdit edit = this.editToBeUndone();
        if (((StructuredEdit)this.editToBeUndone()).editedTime() == this.firstModified) {
            this.firstModified = 0L;
        }
        else if (this.firstModified == 0L) {
            this.firstModified = ((StructuredEdit)this.editToBeUndone()).editedTime();
        }
        final boolean redoable = this.canRedo();
        final boolean changed = this.hasChanged();
        super.undo();
        this.firePropertyChangeEvent("Redo", redoable, this.canRedo());
    }
    
    @Override
    public void undoableEditHappened(final UndoableEditEvent uee) {
        final UndoableEdit edit = uee.getEdit();
        final boolean undoable = this.canUndo();
        final long editTime = System.currentTimeMillis();
        if (this.firstModified == 0L || editTime - this.compoundEdit.editedTime() > 700L) {
            this.compoundEdit.end();
            this.compoundEdit = new StructuredEdit();
        }
        this.compoundEdit.addEdit(edit);
        this.firstModified = ((this.firstModified == 0L) ? this.compoundEdit.editedTime() : this.firstModified);
        if (this.lastEdit() != this.compoundEdit) {
            final boolean changed = this.hasChanged();
            this.addEdit(this.compoundEdit);
            this.firePropertyChangeEvent("Undo", undoable, this.canUndo());
        }
    }
    
    private class StructuredEdit extends CompoundEdit
    {
        private long editedTime;
        
        @Override
        public boolean addEdit(final UndoableEdit edit) {
            final boolean result = super.addEdit(edit);
            if (result && this.editedTime == 0L) {
                this.editedTime = System.currentTimeMillis();
            }
            return result;
        }
        
        @Override
        public boolean canUndo() {
            return this.edits.size() > 0;
        }
        
        protected long editedTime() {
            return this.editedTime;
        }
        
        @Override
        public boolean isInProgress() {
            return false;
        }
    }
}
