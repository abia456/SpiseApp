package com.example.room.Dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.room.Entities.MainCourseItem;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DaoMainCourse_Impl implements DaoMainCourse {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMainCourseItem;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfMainCourseItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfMainCourseItem;

  public DaoMainCourse_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMainCourseItem = new EntityInsertionAdapter<MainCourseItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `MainCourseItem`(`id`,`item_id`,`Name`,`Description`,`price`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainCourseItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getItem_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getItem_id());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPrice());
        }
      }
    };
    this.__deletionAdapterOfMainCourseItem = new EntityDeletionOrUpdateAdapter<MainCourseItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `MainCourseItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainCourseItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfMainCourseItem = new EntityDeletionOrUpdateAdapter<MainCourseItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `MainCourseItem` SET `id` = ?,`item_id` = ?,`Name` = ?,`Description` = ?,`price` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, MainCourseItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getItem_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getItem_id());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getPrice() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPrice());
        }
        stmt.bindLong(6, value.getId());
      }
    };
  }

  @Override
  public Long insertTask(MainCourseItem mainCourseItem) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfMainCourseItem.insertAndReturnId(mainCourseItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTask(MainCourseItem mainCourseItem) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfMainCourseItem.handle(mainCourseItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTask(MainCourseItem mainCourseItem) {
    __db.beginTransaction();
    try {
      __updateAdapterOfMainCourseItem.handle(mainCourseItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<MainCourseItem> fetchAllTasks() {
    final String _sql = "SELECT * FROM MainCourseItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("Description");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final List<MainCourseItem> _result = new ArrayList<MainCourseItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final MainCourseItem _item;
        _item = new MainCourseItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpItem_id;
        _tmpItem_id = _cursor.getString(_cursorIndexOfItemId);
        _item.setItem_id(_tmpItem_id);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _item.setDescription(_tmpDescription);
        final String _tmpPrice;
        _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
        _item.setPrice(_tmpPrice);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public MainCourseItem getTask(int taskId) {
    final String _sql = "SELECT * FROM MainCourseItem WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, taskId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("Description");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final MainCourseItem _result;
      if(_cursor.moveToFirst()) {
        _result = new MainCourseItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpItem_id;
        _tmpItem_id = _cursor.getString(_cursorIndexOfItemId);
        _result.setItem_id(_tmpItem_id);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        _result.setDescription(_tmpDescription);
        final String _tmpPrice;
        _tmpPrice = _cursor.getString(_cursorIndexOfPrice);
        _result.setPrice(_tmpPrice);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
