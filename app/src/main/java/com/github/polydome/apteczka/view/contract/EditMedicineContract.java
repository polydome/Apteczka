package com.github.polydome.apteczka.view.contract;

import com.github.polydome.apteczka.view.contract.common.Contract;

public interface EditMedicineContract {
    interface View extends Contract.View {
        void showEan(String ean);
        void showName(String name);
        void showCommonName(String commonName);
        void showPotency(String potency);
        void showForm(String form);
        void showPackagingSize(int size);
        void showPackagingUnit(String unit);
    }

    interface Presenter extends Contract.Presenter<View> {
        void onCurrentDataRequest(long medicineId);
        void onEanInput(String ean);
        void onSaveMedicine(long id, String ean, String name, String commonName, String potency, String form, int packagingSize, String packagingUnit);
    }
}
