// 
// Decompiled by Procyon v0.5.36
// 

package org.apache.maven.doxia.macro;

import java.util.Iterator;
import org.codehaus.plexus.util.StringUtils;
import org.apache.maven.doxia.sink.Sink;

public class SwfMacro extends AbstractMacro
{
    private static final String EOL;
    
    public void execute(final Sink sink, final MacroRequest request) throws MacroExecutionException {
        String src = "";
        String id = "swf";
        String width = "400";
        String height = "400";
        String quality = "high";
        String menu = "false";
        String loop = "0";
        String play = "true";
        String version = "9,0,45,0";
        String allowScript = "sameDomain";
        final Iterator i = request.getParameters().keySet().iterator();
        while (i.hasNext()) {
            String str = "";
            final String key = i.next();
            if (key.equals("src")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    src = str;
                }
            }
            if (key.equals("id")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    id = str;
                }
            }
            if (key.equals("width")) {
                width = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    width = str;
                }
            }
            if (key.equals("height")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    height = str;
                }
            }
            if (key.equals("quality")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    quality = str;
                }
            }
            if (key.equals("menu")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    menu = str;
                }
            }
            if (key.equals("loop")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    loop = str;
                }
            }
            if (key.equals("play")) {
                str = (String)request.getParameter(key);
                if (StringUtils.isNotEmpty(str)) {
                    play = str;
                }
            }
            if (key.equals("version")) {
                str = (String)request.getParameter(key);
                if (str.equals("6")) {
                    version = "6,0,29,0";
                }
                else if (str.equals("9")) {
                    version = "9,0,45,0";
                }
                else if (StringUtils.isNotEmpty(str)) {
                    version = str;
                }
            }
            if (key.equals("allowScript")) {
                str = (String)request.getParameter(key);
                if (!StringUtils.isNotEmpty(str)) {
                    continue;
                }
                allowScript = str;
            }
        }
        final StringBuffer content = new StringBuffer();
        content.append("<center>").append(SwfMacro.EOL);
        content.append("<object classid=\"clsid27CDB6E-AE6D-11cf-96B8-444553540000\" ").append("codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=").append(version).append("\" width=\"").append(width).append("\" height=\"").append(height).append("\" id=\"").append(id).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"movie\" value=\"").append(src).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"quality\" value=\"").append(quality).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"menu\" value=\"").append(menu).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"loop\" value=\"").append(loop).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"play\" value=\"").append(play).append("\">").append(SwfMacro.EOL);
        content.append("<param name=\"allowScriptAccess\" value=\"").append(allowScript).append("\">");
        content.append("<embed src=\"").append(src).append("\" width=\"").append(width).append("\" height=\"").append(height).append("\" loop=\"").append(loop).append("\" play=\"").append(play).append("\" quality=\"").append(quality).append("\" allowScriptAccess=\"").append(allowScript).append("\" ").append("pluginspage=\"http://www.macromedia.com/go/getflashplayer\" ").append("type=\"application/x-shockwave-flash\" menu=\"").append(menu).append("\">").append(SwfMacro.EOL);
        content.append("</embed>").append(SwfMacro.EOL);
        content.append("</object>").append(SwfMacro.EOL);
        content.append("</center>").append(SwfMacro.EOL);
        sink.rawText(content.toString());
    }
    
    static {
        EOL = System.getProperty("line.separator");
    }
}
