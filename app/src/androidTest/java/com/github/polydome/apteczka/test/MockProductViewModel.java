package com.github.polydome.apteczka.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.github.polydome.apteczka.view.viewmodel.ProductViewModel;

public class MockProductViewModel extends ProductViewModel {
    private final MutableLiveData<String> name = new MutableLiveData<>("");
    private final MutableLiveData<String> commonName = new MutableLiveData<>("");
    private final MutableLiveData<String> form = new MutableLiveData<>("");
    private final MutableLiveData<String> packagingSize = new MutableLiveData<>("");
    private final MutableLiveData<String> packagingUnit = new MutableLiveData<>("");
    private final MutableLiveData<String> potency = new MutableLiveData<>("");
    private long medicineId;

    public MockProductViewModel() {
        super(getProductForMedicineUseCase);
    }

    public void emitName(String name) {
        this.name.postValue(name);
    }

    public void emitCommonName(String commonName) {
        this.commonName.postValue(commonName);
    }

    public void emitForm(String form) {
        this.form.postValue(form);
    }

    public void emitPackagingUnit(String packagingUnit) {
        this.packagingUnit.postValue(packagingUnit);
    }

    public void emitPackagingSize(String packagingSize) {
        this.packagingSize.postValue(packagingSize);
    }

    public void emitPotency(String potency) {
        this.potency.postValue(potency);
    }

    @Override
    public void changeMedicineId(long medicineId) {
        this.medicineId = medicineId;
    }

    public long getMedicineId() {
        return medicineId;
    }

    @Override
    public LiveData<String> getName() {
        return name;
    }

    @Override
    public LiveData<String> getCommonName() {
        return commonName;
    }

    @Override
    public LiveData<String> getForm() {
        return form;
    }

    @Override
    public LiveData<String> getPackagingSize() {
        return packagingSize;
    }

    @Override
    public LiveData<String> getPackagingUnit() {
        return packagingUnit;
    }

    @Override
    public LiveData<String> getPotency() {
        return potency;
    }
}
