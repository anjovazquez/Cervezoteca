package com.cervezoteca.anjov.presentation.presenter;

import android.support.annotation.NonNull;

import com.cervezoteca.anjov.domain.exception.DefaultErrorBundle;
import com.cervezoteca.anjov.domain.exception.ErrorBundle;
import com.cervezoteca.anjov.domain.interactor.DefaultSubscriber;
import com.cervezoteca.anjov.domain.interactor.UseCase;
import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.view.BottleBeersView;
import com.cervezoteca.anjov.presentation.view.TapBeersView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by anjov on 09/10/2016.
 */

public class BottleBeerPresenter implements Presenter {

    private UseCase getBottleBeersUseCase;
    private BottleBeersView bottleBeersView;

    @Inject
    public BottleBeerPresenter(@Named("getBottleBeers") UseCase getBottleBeersUseCase) {
        this.getBottleBeersUseCase = getBottleBeersUseCase;
    }

    public void setView(@NonNull BottleBeersView view) {
        this.bottleBeersView = view;
    }

    public void listBottleBeers() {
        showViewLoading();
        getBottleBeersUseCase.execute(new BottleBeerPresenter.GetBottleBeersSubscriber());
    }

    private final class GetBottleBeersSubscriber extends DefaultSubscriber<List<BottleBeer>> {

        @Override
        public void onCompleted() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<BottleBeer> beers) {
            onBreweriesLoaded(beers);
        }
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        bottleBeersView.showError(errorBundle.getErrorMessage());
    }

    private void showViewLoading(){
        bottleBeersView.showLoading();
    }

    private void hideViewLoading() {
        bottleBeersView.hideLoading();
    }


    private void onBreweriesLoaded(List<BottleBeer> beers){
        bottleBeersView.renderBottleBeers(beers);
    }


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
