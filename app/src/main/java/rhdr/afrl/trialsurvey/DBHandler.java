package rhdr.afrl.trialsurvey;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TrialSurvey";

    private static final String TABLE_PROTOCOL = "Protocol";
    private static final String TABLE_PROTOQUESTIONS = "ProtoQuestions";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMSUBJECTS = "numSubjects";
    private static final String KEY_NUMSHOTCODES = "numShotcodes";

    private static final String KEY_PROTOCOL_ID = "Protocol_id";
    private static final String KEY_QUESTION = "Question";
    private static final String KEY_QUESTION_MIN = "Question_min";
    private static final String KEY_QUESTION_MAX = "Question_max";


    private static final String CREATE_PROTOCOL_TABLE = "CREATE TABLE " + TABLE_PROTOCOL + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_NUMSUBJECTS + " INTEGER," + KEY_NUMSHOTCODES + " INTEGER" + ")";

    private static final String CREATE_PROTOQUESTIONS_TABLE = "CREATE TABLE " + TABLE_PROTOQUESTIONS + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PROTOCOL_ID + " INTEGER,"
            + "FOREIGN KEY(" + KEY_PROTOCOL_ID + ") REFERENCES "
            + TABLE_PROTOCOL + "(id) "
            + KEY_QUESTION + " TEXT," + KEY_QUESTION_MIN + " TEXT," + KEY_QUESTION_MAX + " TEXT" + ")";

     public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROTOCOL_TABLE);
        db.execSQL(CREATE_PROTOQUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROTOCOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROTOQUESTIONS);
        // create new tables
        onCreate(db);
    }

    // Adding new Protocol
    public long addProtocol(Protocol protocol) {
        SQLiteDatabase db = this.getWritableDatabase();
        long flag;

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, protocol.getName()); // Protocol Name
        values.put(KEY_NUMSUBJECTS, protocol.getnumSubjects()); // Protocol numSubjects
        values.put(KEY_NUMSHOTCODES, protocol.getnumShotcodes()); // Protocol numShotcodes

        // Inserting Row
        flag = db.insert(TABLE_PROTOCOL, null, values);
        db.close(); // Closing database connection
        return flag;
    }

    // Getting single protocol
    Protocol getProtocol(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROTOCOL, new String[] { KEY_ID,
                        KEY_NAME, KEY_NUMSUBJECTS, KEY_NUMSHOTCODES }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Protocol protocol = new Protocol(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)));

        cursor.close();
        db.close();
        // return protocol
        return protocol;
    }

    // Getting All Protocols
    public List<Protocol> getAllProtocols() {
        List<Protocol> protocolList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROTOCOL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Protocol protocol = new Protocol();
                protocol.setID(Integer.parseInt(cursor.getString(0)));
                protocol.setName(cursor.getString(1));
                protocol.setnumSubjects(Integer.parseInt(cursor.getString(2)));
                protocol.setnumShotcodes(Integer.parseInt(cursor.getString(3)));
                // Adding protocol to list
                protocolList.add(protocol);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        // return protocol list
        return protocolList;
    }

    // Updating single protocol
    public long updateProtocol(Protocol protocol) {
        long flag;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, protocol.getName());
        values.put(KEY_NUMSUBJECTS, protocol.getnumSubjects());
        values.put(KEY_NUMSHOTCODES, protocol.getnumShotcodes());
        // updating row
        flag = db.update(TABLE_PROTOCOL, values, "_id = ?",
                new String[] { String.valueOf(protocol.getID()) });

        db.close();
        // return number of rows updated should be 1
        return flag;
    }

    // Deleting single protocol
    public void deleteProtocol(Protocol protocol) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROTOCOL, KEY_ID + " = ?",
                new String[]{String.valueOf(protocol.getID())});
        db.close();
    }

    // Getting Protocols Count
    public int getProtocolsCount() {
        int intCount;
        String countQuery = "SELECT  * FROM " + TABLE_PROTOCOL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        intCount = cursor.getCount();
        cursor.close();
        db.close();
        // return count
        return intCount;
    }









}
