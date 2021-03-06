/*
 * Copyright 2000-2013 Enonic AS
 * http://www.enonic.com/license
 */
package com.enonic.cms.core.content.contentdata.legacy.support;

import java.util.List;

import org.jdom.Document;

import com.enonic.cms.core.content.binary.BinaryDataKey;
import com.enonic.cms.core.content.contentdata.ContentData;
import com.enonic.cms.core.content.contentdata.legacy.LegacyFormContentData;

/**
 *
 */
public class FormContentDataParser
{
    private Document contentDataXml;

    private List<BinaryDataKey> binaryDatas;


    @SuppressWarnings({"unchecked"})
    public static ContentData parse( Document contentDataXml, final List<BinaryDataKey> binaryDatas )
    {
        FormContentDataParser parser = new FormContentDataParser( contentDataXml );
        parser.binaryDatas = binaryDatas;
        return parser.parse();
    }

    private FormContentDataParser( Document contentDataXml )
    {
        if ( contentDataXml == null )
        {
            throw new IllegalArgumentException( "Given contentDataXml cannot be null" );
        }

        this.contentDataXml = contentDataXml;
    }

    private ContentData parse()
    {
        LegacyFormContentData contentData = new LegacyFormContentData( contentDataXml );
        contentData.replaceBinaryKeyPlaceholders( binaryDatas );
        return contentData;
    }
}
