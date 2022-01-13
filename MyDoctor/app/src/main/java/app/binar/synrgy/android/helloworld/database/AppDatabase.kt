package app.binar.synrgy.android.helloworld.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.binar.synrgy.android.helloworld.data.local.HomeDAO
import app.binar.synrgy.android.helloworld.data.local.HospitalCache

@Database(version = 1, exportSchema = false, entities = [HospitalCache::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun homeDAO():HomeDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MyDoctor"
                ).build()
            }
            return instance!!
        }
    }
}
