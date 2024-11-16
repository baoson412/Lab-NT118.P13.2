package com.example.lab3_contact_bson_22521247;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Các thông số cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contactsManager";
    private static final String TABLE_CONTACTS = "contacts";

    // Các cột trong bảng
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Nâng cấp cơ sở dữ liệu (hủy bảng cũ và tạo bảng mới)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng cũ nếu tồn tại
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Tạo lại bảng
        onCreate(db);
    }

    // **Thêm một Contact mới**
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // **Lấy tất cả Contact**
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)));
                contact.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE)));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // **Cập nhật một Contact**
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());
        // Trả về số dòng bị ảnh hưởng
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getId())});
    }

    // **Xóa một Contact**
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // **Lấy một Contact qua ID**
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS,
                new String[]{KEY_ID, KEY_NAME, KEY_PHONE},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            Contact contact = new Contact(
                    cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(KEY_PHONE))
            );
            cursor.close();
            return contact;
        }
        return null;
    }
}

