package com.github.polydome.apteczka.view.presenter;

import com.github.polydome.apteczka.view.contract.AddMedicineContract;
import com.github.polydome.domain.usecase.AddMedicineUseCase;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class AddMedicinePresenter implements AddMedicineContract.Presenter {
    private final AddMedicineUseCase addMedicineUseCase;
    private AddMedicineContract.View view;

    private final CompositeDisposable compDisposable = new CompositeDisposable();

    public AddMedicinePresenter(AddMedicineUseCase addMedicineUseCase) {
        this.addMedicineUseCase = addMedicineUseCase;
    }

    @Override
    public void onCreateMedicine(String ean) {
        compDisposable.add(
            addMedicineUseCase.execute(ean).subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long medicineId) {
                    requireView().showMedicineEditor(medicineId);
                }
            })
        );
    }

    @Override
    public void attach(AddMedicineContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        compDisposable.dispose();
        this.view = null;
    }

    private AddMedicineContract.View requireView() {
        if (this.view == null)
            throw new IllegalStateException("No view attached");
        else
            return view;
    }
}
