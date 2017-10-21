package connorhenke.com.dnd5000;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM character")
    Flowable<List<Character>> getAll();

    @Query("SELECT * FROM character WHERE id = :id LIMIT 1")
    Flowable<Character> get(int id);

    @Insert
    void insertAll(Character... characters);
}
