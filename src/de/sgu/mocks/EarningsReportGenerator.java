package de.sgu.mocks;

import de.sgu.mocks.connection.ConnectionException;
import de.sgu.mocks.connection.ConnectionProvider;

import java.io.IOException;
import java.sql.Connection;

public class EarningsReportGenerator {

    private ConnectionProvider connectionProvider;
    private EarningsReportDataCollector earningsReportDataCollector;
    private ReportProvider reportProvider;

    public EarningsReportGenerator(ConnectionProvider connectionProvider, EarningsReportDataCollector earningsReportDataCollector, ReportProvider reportProvider) {
        this.connectionProvider = connectionProvider;
        this.earningsReportDataCollector = earningsReportDataCollector;
        this.reportProvider = reportProvider;
    }

    public Report generateReport(String mall) throws GenerateReportException{
        try {
            Connection connection = this.connectionProvider.getConnection(mall);
            Earnings earnings = this.earningsReportDataCollector.getEarnings(connection);
            return this.reportProvider.createReport(earnings);
        } catch (ConnectionException | IOException e) {
            throw new GenerateReportException(e);
        }
    }

}
