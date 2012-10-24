package com.enonic.cms.core.portal.datasource2.handler.content;

import org.jdom.Document;

import com.enonic.cms.framework.xml.XMLDocument;

import com.enonic.cms.core.portal.datasource2.handler.DataSourceHandler;
import com.enonic.cms.core.portal.datasource.handler.DataSourceRequest;

public final class GetContentByQueryHandler
    extends DataSourceHandler
{
    public GetContentByQueryHandler()
    {
        super( "getContentByQuery" );
    }

    @Override
    public Document handle( final DataSourceRequest req )
        throws Exception
    {
        final String query = req.param( "query" ).asString( "" );
        final String orderBy = req.param( "orderBy" ).asString( "" );
        final int index = req.param( "index" ).asInteger( 0 );
        final int count = req.param( "count" ).asInteger( 10 );
        final boolean includeData = req.param( "includeData" ).asBoolean( true );
        final int childrenLevel = req.param( "childrenLevel" ).asInteger( 1 );
        final int parentLevel = req.param( "parentLevel" ).asInteger( 0 );

        XMLDocument document = dataSourceService.getContentByQuery( req, query, orderBy, index, count, includeData, childrenLevel, parentLevel );
        return document.getAsJDOMDocument();
    }
}
