package de.sgu.mocks;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.easymock.EasyMock;
import org.junit.Test;

public class EarningsReportGeneratorTest {

    @Test
    public void testGenerateReport() {
        
        ConnectionProvider connectionProvider = EasyMock.createMock(ConnectionProvider.class);
        Connection connection = EasyMock.createMock(Connection.class);
        EasyMock.expect(connectionProvider.getConnection("aeon")).andReturn(connection);
        
        EarningsReportDataCollector earningsReportDataCollector = EasyMock.createMock(EarningsReportDataCollector.class);
        
        Earnings earnings = new Earnings();
        EasyMock.expect(earningsReportDataCollector.getEarnings(connection)).andReturn(earnings);
        
        ReportProvider reportProvider = EasyMock.createMock(ReportProvider.class);
        EasyMock.expect(reportProvider.createReport(earnings)).andReturn(new Report());
        
        EasyMock.replay(connectionProvider, earningsReportDataCollector, reportProvider);
        EarningsReportGenerator erg = new EarningsReportGenerator(connectionProvider, earningsReportDataCollector, reportProvider);
        Report report = erg.generateReport("aeon");
        EasyMock.verify(connectionProvider, earningsReportDataCollector, reportProvider);
        
        
        assertNotNull(report);
    }

}
