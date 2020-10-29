package seedu.duke.utilities;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.storage.Storage;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "src/test/java/seedu/duke/utilities";
    private static final String NON_EXISTANT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new Storage(null, null));
    }

    @Test
    public void load_validFormat() throws Exception {
        SetupTransactionData setupData = new SetupTransactionData();
        TransactionList actualTransaction = getStorage("expensesTest.txt").load();
        TransactionList expectedTransaction = setupData.loadTransactionData();

        assertEquals(expectedTransaction.getTransactionList().size(),actualTransaction.getTransactionList().size());
    }


    private Storage getStorage(String fileName) throws Exception {
        return new Storage(TEST_DATA_FOLDER, fileName);
    }

}
