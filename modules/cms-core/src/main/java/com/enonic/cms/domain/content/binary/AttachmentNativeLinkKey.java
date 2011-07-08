/*
 * Copyright 2000-2011 Enonic AS
 * http://www.enonic.com/license
 */
package com.enonic.cms.domain.content.binary;

import com.enonic.cms.domain.content.AbstractContentBasedNativeLinkKey;
import com.enonic.cms.domain.content.ContentKey;

/**
 * Feb 15, 2010
 */
public class AttachmentNativeLinkKey
    extends AbstractContentBasedNativeLinkKey
{
    private String extension;

    public AttachmentNativeLinkKey( ContentKey contentKey )
    {
        super( contentKey );
    }

    public String getExtension()
    {
        return extension;
    }

    void setExtension( String extension )
    {
        this.extension = extension;
    }

    public String asUrlRepresentation()
    {
        String str = getContentKey().toString();
        if ( getExtension() != null )
        {
            str = str + "." + getExtension();
        }
        return str;
    }
}
