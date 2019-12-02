package com.example.room.Database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.room.Dao.DaoDessert;
import com.example.room.Dao.DaoDessert_Impl;
import com.example.room.Dao.DaoDrink;
import com.example.room.Dao.DaoDrink_Impl;
import com.example.room.Dao.DaoMainCourse;
import com.example.room.Dao.DaoMainCourse_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class DatabaseClass_Impl extends DatabaseClass {
  private volatile DaoDessert _daoDessert;

  private volatile DaoDrink _daoDrink;

  private volatile DaoMainCourse _daoMainCourse;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DessertItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `item_id` TEXT, `Name` TEXT, `Description` TEXT, `price` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `DrinkItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `item_id` TEXT, `Name` TEXT, `price` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `MainCourseItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `item_id` TEXT, `Name` TEXT, `Description` TEXT, `price` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"c4d0ae47e2f8ebe94cde1fb554d1733c\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `DessertItem`");
        _db.execSQL("DROP TABLE IF EXISTS `DrinkItem`");
        _db.execSQL("DROP TABLE IF EXISTS `MainCourseItem`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDessertItem = new HashMap<String, TableInfo.Column>(5);
        _columnsDessertItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDessertItem.put("item_id", new TableInfo.Column("item_id", "TEXT", false, 0));
        _columnsDessertItem.put("Name", new TableInfo.Column("Name", "TEXT", false, 0));
        _columnsDessertItem.put("Description", new TableInfo.Column("Description", "TEXT", false, 0));
        _columnsDessertItem.put("price", new TableInfo.Column("price", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDessertItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDessertItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDessertItem = new TableInfo("DessertItem", _columnsDessertItem, _foreignKeysDessertItem, _indicesDessertItem);
        final TableInfo _existingDessertItem = TableInfo.read(_db, "DessertItem");
        if (! _infoDessertItem.equals(_existingDessertItem)) {
          throw new IllegalStateException("Migration didn't properly handle DessertItem(com.example.room.Entities.DessertItem).\n"
                  + " Expected:\n" + _infoDessertItem + "\n"
                  + " Found:\n" + _existingDessertItem);
        }
        final HashMap<String, TableInfo.Column> _columnsDrinkItem = new HashMap<String, TableInfo.Column>(4);
        _columnsDrinkItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDrinkItem.put("item_id", new TableInfo.Column("item_id", "TEXT", false, 0));
        _columnsDrinkItem.put("Name", new TableInfo.Column("Name", "TEXT", false, 0));
        _columnsDrinkItem.put("price", new TableInfo.Column("price", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDrinkItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDrinkItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDrinkItem = new TableInfo("DrinkItem", _columnsDrinkItem, _foreignKeysDrinkItem, _indicesDrinkItem);
        final TableInfo _existingDrinkItem = TableInfo.read(_db, "DrinkItem");
        if (! _infoDrinkItem.equals(_existingDrinkItem)) {
          throw new IllegalStateException("Migration didn't properly handle DrinkItem(com.example.room.Entities.DrinkItem).\n"
                  + " Expected:\n" + _infoDrinkItem + "\n"
                  + " Found:\n" + _existingDrinkItem);
        }
        final HashMap<String, TableInfo.Column> _columnsMainCourseItem = new HashMap<String, TableInfo.Column>(5);
        _columnsMainCourseItem.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsMainCourseItem.put("item_id", new TableInfo.Column("item_id", "TEXT", false, 0));
        _columnsMainCourseItem.put("Name", new TableInfo.Column("Name", "TEXT", false, 0));
        _columnsMainCourseItem.put("Description", new TableInfo.Column("Description", "TEXT", false, 0));
        _columnsMainCourseItem.put("price", new TableInfo.Column("price", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMainCourseItem = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMainCourseItem = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMainCourseItem = new TableInfo("MainCourseItem", _columnsMainCourseItem, _foreignKeysMainCourseItem, _indicesMainCourseItem);
        final TableInfo _existingMainCourseItem = TableInfo.read(_db, "MainCourseItem");
        if (! _infoMainCourseItem.equals(_existingMainCourseItem)) {
          throw new IllegalStateException("Migration didn't properly handle MainCourseItem(com.example.room.Entities.MainCourseItem).\n"
                  + " Expected:\n" + _infoMainCourseItem + "\n"
                  + " Found:\n" + _existingMainCourseItem);
        }
      }
    }, "c4d0ae47e2f8ebe94cde1fb554d1733c", "d9e707c0fe12435d94b8e1a9645d0747");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "DessertItem","DrinkItem","MainCourseItem");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `DessertItem`");
      _db.execSQL("DELETE FROM `DrinkItem`");
      _db.execSQL("DELETE FROM `MainCourseItem`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DaoDessert daoDessert() {
    if (_daoDessert != null) {
      return _daoDessert;
    } else {
      synchronized(this) {
        if(_daoDessert == null) {
          _daoDessert = new DaoDessert_Impl(this);
        }
        return _daoDessert;
      }
    }
  }

  @Override
  public DaoDrink daoDrink() {
    if (_daoDrink != null) {
      return _daoDrink;
    } else {
      synchronized(this) {
        if(_daoDrink == null) {
          _daoDrink = new DaoDrink_Impl(this);
        }
        return _daoDrink;
      }
    }
  }

  @Override
  public DaoMainCourse daoMainCourse() {
    if (_daoMainCourse != null) {
      return _daoMainCourse;
    } else {
      synchronized(this) {
        if(_daoMainCourse == null) {
          _daoMainCourse = new DaoMainCourse_Impl(this);
        }
        return _daoMainCourse;
      }
    }
  }
}
