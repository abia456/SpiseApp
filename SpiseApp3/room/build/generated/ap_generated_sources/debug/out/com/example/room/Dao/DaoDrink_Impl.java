package com.example.room.Dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.room.Entities.DrinkItem;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class DaoDrink_Impl implements DaoDrink {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDrinkItem;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDrinkItem;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDrinkItem;

  public DaoDrink_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDrinkItem = new EntityInsertionAdapter<DrinkItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `DrinkItem`(`id`,`item_id`,`Name`,`price`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DrinkItem value) {
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
        if (value.getPrice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrice());
        }
      }
    };
    this.__deletionAdapterOfDrinkItem = new EntityDeletionOrUpdateAdapter<DrinkItem>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `DrinkItem` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DrinkItem value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDrinkItem = new EntityDeletionOrUpdateAdapter<DrinkItem>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `DrinkItem` SET `id` = ?,`item_id` = ?,`Name` = ?,`price` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DrinkItem value) {
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
        if (value.getPrice() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPrice());
        }
        stmt.bindLong(5, value.getId());
      }
    };
  }

  @Override
  public Long insertTask(DrinkItem drinkItem) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfDrinkItem.insertAndReturnId(drinkItem);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTask(DrinkItem drinkItem) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDrinkItem.handle(drinkItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTask(DrinkItem drinkItem) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDrinkItem.handle(drinkItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<DrinkItem> fetchAllTasks() {
    final String _sql = "SELECT * FROM DrinkItem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final List<DrinkItem> _result = new ArrayList<DrinkItem>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DrinkItem _item;
        _item = new DrinkItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpItem_id;
        _tmpItem_id = _cursor.getString(_cursorIndexOfItemId);
        _item.setItem_id(_tmpItem_id);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
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
  public DrinkItem getTask(int taskId) {
    final String _sql = "SELECT * FROM DrinkItem WHERE id =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, taskId);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfPrice = _cursor.getColumnIndexOrThrow("price");
      final DrinkItem _result;
      if(_cursor.moveToFirst()) {
        _result = new DrinkItem();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpItem_id;
        _tmpItem_id = _cursor.getString(_cursorIndexOfItemId);
        _result.setItem_id(_tmpItem_id);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result.setName(_tmpName);
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
