package de.sgu.mocks;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import de.sgu.mocks.connection.ConnectionException;
import de.sgu.mocks.connection.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class EarningsReportGeneratorTest {

    private ConnectionProvider connectionProvider;
    private EarningsReportDataCollector earningsReportDataCollector;
    private ReportProvider reportProvider;
    private EarningsReportGenerator erg;

    @Before
    public void setup() {
        connectionProvider = EasyMock.createMock(ConnectionProvider.class);
        earningsReportDataCollector = EasyMock.createMock(EarningsReportDataCollector.class);
        reportProvider = EasyMock.createMock(ReportProvider.class);

        erg = new EarningsReportGenerator(connectionProvider, earningsReportDataCollector, reportProvider);
    }

    @Test
    public void testGenerateReportSucceeds() {

        try {
            Connection connection = EasyMock.createMock(Connection.class);
            EasyMock.expect(connectionProvider.getConnection("aeon")).andReturn(connection);

            Earnings earnings = new Earnings();
            EasyMock.expect(earningsReportDataCollector.getEarnings(connection)).andReturn(earnings);

            EasyMock.expect(reportProvider.createReport(earnings)).andReturn(new Report());

            EasyMock.replay(connectionProvider, earningsReportDataCollector, reportProvider);
            Report report = erg.generateReport("aeon");
            EasyMock.verify(connectionProvider, earningsReportDataCollector, reportProvider);

            assertNotNull(report);
        } catch (ConnectionException | GenerateReportException | IOException gre) {
            fail("Unexpected exception!");
        }
    }

    @Test(expected=GenerateReportException.class)
    public void testGetConnectionFailsAndThrowsReportGenerationFailedException() throws GenerateReportException, ConnectionException, IOException{
        
        EasyMock.expect(this.connectionProvider.getConnection("aeon")).andThrow(new ConnectionException());
        EasyMock.replay(this.connectionProvider);
        Report report = erg.generateReport("aeon");
        EasyMock.verify(this.connectionProvider);
    }

}
