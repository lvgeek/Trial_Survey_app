/**
 * Created by dave on 11/11/15.
 */
package rhdr.afrl.trialsurvey;

        import java.util.ArrayList;
        import java.util.List;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "TrialSurvey";

    // Protocol table name
    private static final String TABLE_PROTOCOL = "protocol";

    // Protocol Table Columns namesrial
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMSUBJECTS = "numSubjects";
    private static final String KEY_NUMSHOTCODES = "numShotcodes";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PROTOCOL_TABLE = "CREATE TABLE " + TABLE_PROTOCOL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NUMSUBJECTS + " TEXT" + KEY_NUMSHOTCODES + " TEXT" + ")";
        db.execSQL(CREATE_PROTOCOL_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROTOCOL);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Protocol
    public long addProtocol(Protocol protocol) {
        SQLiteDatabase db = this.getWritableDatabase();
        long flag = 0;

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, protocol.getName()); // Protocol Name
        values.put(KEY_NUMSUBJECTS, protocol.getnumSubjects()); // Protocol numSubjects
        values.put(KEY_NUMSUBJECTS, protocol.getnumShotcodes()); // Protocol numShotcodes

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
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return protocol
        return protocol;
    }

    // Getting All Protocols
    public List<Protocol> getAllProtocols() {
        List<Protocol> protocolList = new ArrayList<Protocol>();
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
                protocol.setnumSubjects(cursor.getString(2));
                protocol.setnumShotcodes(cursor.getString(3));
                // Adding protocol to list
                protocolList.add(protocol);
            } while (cursor.moveToNext());
        }

        // return protocol list
        return protocolList;
    }

    // Updating single protocol
    public int updateProtocol(Protocol protocol) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, protocol.getName());
        values.put(KEY_NUMSUBJECTS, protocol.getnumSubjects());
        values.put(KEY_NUMSHOTCODES, protocol.getnumShotcodes());
        // updating row
        return db.update(TABLE_PROTOCOL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(protocol.getID()) });
    }

    // Deleting single protocol
    public void deleteProtocol(Protocol protocol) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROTOCOL, KEY_ID + " = ?",
                new String[] { String.valueOf(protocol.getID()) });
        db.close();
    }


    // Getting Protocols Count
    public int getProtocolsCount() {
        int intCount = -1;
        String countQuery = "SELECT  * FROM " + TABLE_PROTOCOL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        intCount = cursor.getCount();
        cursor.close();

        // return count
        return intCount;
    }

}
