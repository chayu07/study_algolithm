package term.handlersocket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketOptions;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import com.google.code.hs4j.FindOperator;
import com.google.code.hs4j.HSClient;
import com.google.code.hs4j.IndexSession;
import com.google.code.hs4j.ModifyStatement;
import com.google.code.hs4j.exception.HandlerSocketException;
import com.google.code.hs4j.impl.HSClientImpl;
import com.google.code.hs4j.network.core.SocketOption;

public class HandlersocketConnector {
	public final static int READ_PORT =  9998;
	public final static int WRITE_PORT = 9999;
	private String hostName;

	private HSClient readClient;
	private HSClient writeClient;

	public HandlersocketConnector(String hostName) {
		this.hostName = hostName;

	}


	public void makeReadClient() throws IOException {
		readClient = new HSClientImpl(new InetSocketAddress(hostName, READ_PORT),500);
	}

	public void makeWriteClient() throws IOException {
		writeClient = new HSClientImpl(new InetSocketAddress(hostName, WRITE_PORT),500);
	}

	public void writeTest(int writeCount, int threadNo) throws IOException, InterruptedException, TimeoutException, HandlerSocketException {


		IndexSession session = writeClient.openIndexSession("mysqlslap", "user",
                "PRIMARY", new String[] {"user_content","user_content1","user_content2","user_content3"});

		//contents length = 235 byte
		String contents = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345";

		//moreContents lenght = 20 byte
		String moreContents = "12345678901234567890";

        ModifyStatement stmt = session.createStatement();

        for(int i = 0; i < writeCount; i++) {
        	stmt.setString(1, contents + moreContents);
        	stmt.setString(2, contents + moreContents);
        	stmt.setString(3, contents + moreContents);
        	stmt.setString(4, contents);
        	stmt.insert();
        }
	}

	public void readTest(int threadNo) throws IOException, InterruptedException, TimeoutException, HandlerSocketException, SQLException {

		IndexSession session = readClient.openIndexSession("mysqlslap", "user",
                "PRIMARY", new String[] {"user_id","user_content","user_content1","user_content2","user_content3"});


		Random randomUserId = new Random(10000);
//		String[] keys = { "10000" };

		for (int i = 0; i < 10000; i++) {
			String[] keys = { Integer.toString(i) };
			ResultSet rs = session.find(keys,FindOperator.EQ,1,0);
		}

	}

	public void shutdownClient() throws IOException {
		if(readClient != null) readClient.shutdown();
		if(writeClient != null) writeClient.shutdown();
	}

	public static void main(String[] args) {
		HandlersocketConnector connector = new HandlersocketConnector("54.199.184.158");

		try {
//			connector.writeTest(10000, threadNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
