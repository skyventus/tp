package seedu.duke.storage;

import seedu.duke.data.TransactionList;
import seedu.duke.exception.IllegalValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_FILENAME = "mytransaction.txt";
    public static final String DEFAULT_FILE_DIRECTORY = "expenses";

    public String fileDirectory;
    public String filePath;

    public Storage() {
        this.fileDirectory = DEFAULT_FILE_DIRECTORY;


        File directory = new File(fileDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        this.filePath = directory.getAbsolutePath() + "\\" + DEFAULT_FILENAME;
    }

    public Storage(String fileDirectory, String fileName) throws InvalidStorageFilePathException {
        this.filePath = fileName;
        this.fileDirectory = fileDirectory;

        if (!isValidFilePath(fileName)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    public void save(TransactionList transactionsList) throws StorageOperationException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            List<String> encodedTransactionList = TransactionListEncoded.encodeTransactionList(transactionsList);
            for (String s : encodedTransactionList) {
                fw.write(s);
            }
            fw.close();
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + filePath);
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    public TransactionList load() throws IllegalValueException, InterruptedException {

        if (!Files.exists(Paths.get(filePath))
                || !Files.isRegularFile(Paths.get(filePath))) {
            return new TransactionList();
        }

        try {
            return TransactionListDecoded.decodeTransactions(Files.readAllLines(Paths.get(filePath)));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IllegalValueException | IOException e) {
            throw new IllegalValueException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */

    public class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

}
