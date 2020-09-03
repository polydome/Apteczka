package com.github.polydome.apteczka.data.repository;

import com.github.polydome.apteczka.data.dao.MedicineDao;
import com.github.polydome.apteczka.data.entity.MedicineEntity;
import com.github.polydome.apteczka.domain.model.Medicine;
import com.github.polydome.apteczka.domain.repository.MedicineRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class RoomMedicineRepository implements MedicineRepository {
    private final MedicineDao medicineDao;

    public RoomMedicineRepository(MedicineDao medicineDao) {
        this.medicineDao = medicineDao;
    }

    @Override
    public Single<Long> create(Medicine medicine) {
        return medicineDao.create(new MedicineEntity(medicine));
    }

    @Override
    public Maybe<Medicine> findById(long id) {
        return medicineDao.findById(id).map(new Function<MedicineEntity, Medicine>() {
            @Override
            public Medicine apply(MedicineEntity medicineEntity) {
                return medicineEntity.toMedicine();
            }
        });
    }

    public Observable<List<Long>> ids() {
        return medicineDao.ids();
    }

    @Override
    public Completable update(Medicine medicine) {
        return null;
    }
}
