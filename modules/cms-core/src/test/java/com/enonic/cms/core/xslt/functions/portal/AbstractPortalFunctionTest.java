package com.enonic.cms.core.xslt.functions.portal;

import com.enonic.cms.core.xslt.functions.AbstractXsltFunctionTest;
import com.enonic.cms.core.xslt.lib.PortalFunctionsMediator;

public abstract class AbstractPortalFunctionTest
    extends AbstractXsltFunctionTest<AbstractPortalFunction>
{
    protected final void processTemplate( final PortalFunctionsMediator functions, final String baseName )
        throws Exception
    {
        this.function.setPortalFunctions( functions );
        processTemplate( baseName );
    }
}