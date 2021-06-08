// 
// Decompiled by Procyon v0.5.36
// 

package com.mks.api.commands;

import com.mks.api.OptionList;
import com.mks.api.response.APIException;
import com.mks.api.response.Response;

interface ICommandBase
{
    Response execute(final boolean p0) throws APIException;
    
    Response execute(final String[] p0, final boolean p1) throws APIException;
    
    Response execute(final String p0, final boolean p1) throws APIException;
    
    void addOptionList(final OptionList p0);
}
