package com.cervezoteca.anjov.presentation.presenter;

import android.support.annotation.NonNull;

import com.cervezoteca.anjov.domain.exception.DefaultErrorBundle;
import com.cervezoteca.anjov.domain.exception.ErrorBundle;
import com.cervezoteca.anjov.domain.interactor.DefaultSubscriber;
import com.cervezoteca.anjov.domain.interactor.UseCase;
import com.cervezoteca.anjov.domain.model.TapBeer;
import com.cervezoteca.anjov.presentation.view.TapBeersView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by anjov on 09/10/2016.
 */

public class TapBeerPresenter implements Presenter {

    private UseCase getTapBeersUseCase;
    private TapBeersView tapBeersView;

    @Inject
    public TapBeerPresenter(@Named("getTapBeers") UseCase getTapBeersUseCase) {
        this.getTapBeersUseCase = getTapBeersUseCase;
    }

    public void setView(@NonNull TapBeersView view) {
        this.tapBeersView = view;
    }

    public void listTapBeers() {
        showViewLoading();
        getTapBeersUseCase.execute(new TapBeerPresenter.GetTapBeersSubscriber());
    }

    private final class GetTapBeersSubscriber extends DefaultSubscriber<List<TapBeer>> {

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
        public void onNext(List<TapBeer> beers) {
            onBreweriesLoaded(beers);
        }
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        tapBeersView.showError(errorBundle.getErrorMessage());
    }

    private void showViewLoading(){
        tapBeersView.showLoading();
    }

    private void hideViewLoading() {
        tapBeersView.hideLoading();
    }


    private void onBreweriesLoaded(List<TapBeer> beers){
        tapBeersView.renderTapBeers(beers);
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
