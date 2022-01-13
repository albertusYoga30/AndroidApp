package app.binar.synrgy.android.helloworld.data.local

import androidx.room.*

@Dao
interface HomeDAO {

    @Query("SELECT * FROM Hospital")
    suspend fun getAllHospital():List<HospitalCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHospital(vararg  hospitals:HospitalCache)

    @Delete
    suspend fun deleteHospital(hospital:HospitalCache)

    @Update
    suspend fun updateHospital(hospital:HospitalCache)

}