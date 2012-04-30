package com.enonic.cms.core.search.query.factories;

import org.elasticsearch.index.query.QueryBuilder;
import org.joda.time.DateTime;
import org.junit.Test;

import com.enonic.cms.core.search.query.QueryFieldAndValue;
import com.enonic.cms.core.search.query.QueryTranslatorBaseTest;


public class TermQueryBuilderFactoryTest
    extends QueryTranslatorBaseTest
{
    private final TermQueryBuilderFactory termQueryBuilderFactory = new TermQueryBuilderFactory();

    @Test
    public void testIdQueries()
    {
        String expected = "{\n" +
            "  \"ids\" : {\n" +
            "    \"type\" : \"content\",\n" +
            "    \"values\" : [ \"123\" ]\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "key", "123" ) );
        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testIdQueriesWithNumber()
    {
        String expected = "{\n" +
            "  \"ids\" : {\n" +
            "    \"type\" : \"content\",\n" +
            "    \"values\" : [ \"123\" ]\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "key", 123 ) );
        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testWildcardQuery()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"_all_userdata\" : \"123\"\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data/*", "123" ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testWildcardQueryWithNumeric()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"_all_userdata.number\" : 123.0\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data/*", 123 ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testWildcardQueryWithDate()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"_all_userdata.date\" : \"2010-08-01T08:00:00.000Z\"\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder =
            termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data/*", new DateTime( 2010, 8, 1, 10, 0 ) ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }


    @Test
    public void testContentdataQueryNumber()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"data_person_age.number\" : 123.0\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data_person_age", 123 ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );


    }

    @Test
    public void testContentdataQueryDate()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"data_person_birthdate.date\" : \"2010-08-01T08:00:00.000Z\"\n" +
            "  }\n" + "}";

        DateTime dateTime = new DateTime( 2010, 8, 1, 10, 00 );

        final QueryBuilder queryBuilder =
            termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data_person_birthdate", dateTime ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testContentdataQueryDateAsString()
    {
        String expected = "{\n" +
            "  \"term\" : {\n" +
            "    \"data_person_birthdate\" : \"01:08:10\"\n" +
            "  }\n" + "}";

        DateTime dateTime = new DateTime( 2010, 8, 1, 10, 00 );

        final QueryBuilder queryBuilder =
            termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "data_person_birthdate", dateTime.toString( "dd:MM:yy" ) ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }

    @Test
    public void testWrapInHasChild()
    {
        String expected = "{\n" +
            "  \"has_child\" : {\n" +
            "    \"query\" : {\n" +
            "      \"term\" : {\n" +
            "        \"_all_userdata.number\" : 123.0\n" +
            "      }\n" +
            "    },\n" +
            "    \"type\" : \"binaries\"\n" +
            "  }\n" +
            "}";

        final QueryBuilder queryBuilder = termQueryBuilderFactory.buildTermQuery( new QueryFieldAndValue( "attachments/*", 123 ) );

        compareStringsIgnoreFormatting( expected, queryBuilder.toString() );
    }


}