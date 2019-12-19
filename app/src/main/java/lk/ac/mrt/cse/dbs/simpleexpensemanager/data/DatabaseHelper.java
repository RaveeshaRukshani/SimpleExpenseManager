package lk.ac.mrt.cse.dbs.simpleexpensemanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

 private static final String DATABASE_NAME = "170527L";
    private static final int DATABASE_VERSION = 3;

    //    create tables
    public static final String ACCOUNTS_TABLE = "accounts";
    public static final String TRANSACTIONS_TABLE = "transactions";

    //    create keys
    public static final String BANK_NAME_KEY = "bankName";
    public static final String ACCOUNT_NO_KEY = "accountNo";
    public static final String ACCOUNT_HOLDER_NAME_KEY = "accountHolderName";
    public static final String BALANCE_KEY = "balance";
    private static final String TRANSACTION_ID_KEY = "id";
    public static final String EXPENSE_TYPE_KEY = "expenseType";
    public static final String AMOUNT_KEY = "amount";
    public static final String DATE_KEY = "date";

    public static DatabaseHelper instance;

    public static DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    };

    private static final String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + ACCOUNTS_TABLE + "("
            + ACCOUNT_NO_KEY + " TEXT PRIMARY KEY," + BANK_NAME_KEY + " TEXT,"
            + ACCOUNT_HOLDER_NAME_KEY + " TEXT," + BALANCE_KEY + " REAL" + ")";

    private static final String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TRANSACTIONS_TABLE + "("
            + TRANSACTION_ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT," + DATE_KEY + " TEXT," + ACCOUNT_NO_KEY + " TEXT,"
            + EXPENSE_TYPE_KEY + " TEXT," + AMOUNT_KEY + " REAL," + "FOREIGN KEY(" + ACCOUNT_NO_KEY +
            ") REFERENCES "+ ACCOUNTS_TABLE +"(" + ACCOUNT_NO_KEY + ") )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNTS_TABLE);
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS '" + ACCOUNTS_TABLE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TRANSACTIONS_TABLE + "'");

        // Create tables again
        onCreate(db);
    }
}
