// 
// Decompiled by Procyon v0.5.36
// 

package org.codehaus.groovy.binding;

import java.beans.PropertyChangeEvent;
import java.util.HashSet;
import java.util.Set;
import java.beans.PropertyChangeListener;

public class PropertyPathFullBinding extends AbstractFullBinding implements PropertyChangeListener
{
    Set updateObjects;
    BindPath[] bindPaths;
    boolean bound;
    
    public PropertyPathFullBinding() {
        this.updateObjects = new HashSet();
    }
    
    public void bind() {
        this.updateObjects.clear();
        for (final BindPath bp : this.bindPaths) {
            bp.addAllListeners(this, bp.currentObject, this.updateObjects);
        }
        this.bound = true;
    }
    
    public void unbind() {
        this.updateObjects.clear();
        for (final BindPath path : this.bindPaths) {
            path.removeListeners();
        }
        this.bound = false;
    }
    
    public void rebind() {
        if (this.bound) {
            this.bind();
        }
    }
    
    public void propertyChange(final PropertyChangeEvent evt) {
        if (this.updateObjects.contains(evt.getSource())) {
            for (final BindPath bp : this.bindPaths) {
                final Set newUpdates = new HashSet();
                bp.updatePath(this, bp.currentObject, newUpdates);
                this.updateObjects = newUpdates;
            }
        }
        this.update();
    }
}
