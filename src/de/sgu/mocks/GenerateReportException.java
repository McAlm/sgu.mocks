package de.sgu.mocks;

import de.sgu.mocks.connection.ConnectionException;

public class GenerateReportException extends Exception {

    public GenerateReportException(ConnectionException e) {
        super(e);
    }

}
