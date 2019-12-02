package com.example.room.Dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.room.Entities.DessertItem;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DaoDessert_Impl implements DaoDessert {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDessertItem;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDessertItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDessertItem;

  public DaoDessert_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDessertItem = new EntityInsertionAdapter<DessertItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DessertItem`(`id`,`item_id`,`Name`,`Description`,`price`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DessertItem value) {
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
    this.__deletionAdapterOfDessertItem = new EntityDeletionOrUpdateAdapter<DessertItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DessertItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DessertItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDessertItem = new EntityDeletionOrUpdateAdapter<DessertItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DessertItem` SET `id` = ?,`item_id` = ?,`Name` = ?,`Description` = ?,`price` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DessertItem value) {
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
  public Long insertTask(DessertItem dessertItem) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDessertItem.insertAndReturnId(dessertItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTask(DessertItem dessertItem) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDessertItem.handle(dessertItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTask(DessertItem dessertItem) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDessertItem.handle(dessertItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<DessertItem> fetchAllTasks() {
    final String _sql = "SELECT * FROM DessertItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("Description");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final List<DessertItem> _result = new ArrayList<DessertItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DessertItem _item;
        _item = new DessertItem();
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
  public DessertItem getTask(int taskId) {
    final String _sql = "SELECT * FROM DessertItem WHERE id =?";
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
      final DessertItem _result;
      if(_cursor.moveToFirst()) {
        _result = new DessertItem();
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
