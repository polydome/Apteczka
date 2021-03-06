package com.github.polydome.apteczka.di.module;

import com.github.polydome.apteczka.view.contract.EditMedicineContract;
import com.github.polydome.apteczka.view.contract.ShowMedicineContract;
import com.github.polydome.apteczka.view.presenter.EditMedicinePresenter;
import com.github.polydome.apteczka.view.presenter.ShowMedicinePresenter;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DomainModule.class, SchedulerModule.class})
public class PresentationModule {
    @Provides
    public EditMedicineContract.Presenter editMedicinePresenter(EditMedicinePresenter editMedicinePresenter) {
        return editMedicinePresenter;
    }

    @Provides
    public ShowMedicineContract.Presenter showMedicinePresenter(ShowMedicinePresenter showMedicinePresenter) {
        return showMedicinePresenter;
    }
}
