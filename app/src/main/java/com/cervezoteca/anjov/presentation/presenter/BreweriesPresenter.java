package com.cervezoteca.anjov.presentation.presenter;

import android.support.annotation.NonNull;

import com.cervezoteca.anjov.domain.exception.DefaultErrorBundle;
import com.cervezoteca.anjov.domain.exception.ErrorBundle;
import com.cervezoteca.anjov.domain.interactor.DefaultSubscriber;
import com.cervezoteca.anjov.domain.interactor.UseCase;
import com.cervezoteca.anjov.domain.model.Brewery;
import com.cervezoteca.anjov.presentation.view.BreweriesView;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by anjov on 07/10/2016.
 */

public class BreweriesPresenter implements Presenter {

    private UseCase getBreweriesUseCase;
    private BreweriesView breweriesView;

    @Inject
    public BreweriesPresenter(@Named("getBreweries") UseCase getBreweriesUseCase) {
        this.getBreweriesUseCase = getBreweriesUseCase;
    }

    public void setView(@NonNull BreweriesView view) {
        this.breweriesView = view;
    }

    public void listBreweries() {
        getBreweriesUseCase.execute(new GetBreweriesSubscriber());
    }

    private final class GetBreweriesSubscriber extends DefaultSubscriber<List<Brewery>> {

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
        public void onNext(List<Brewery> breweries) {
            onBreweriesLoaded(breweries);
        }
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        breweriesView.showError(errorBundle.getErrorMessage());
    }

    private void showViewLoading(){
        breweriesView.showLoading();
    }

    private void hideViewLoading() {
        breweriesView.hideLoading();
    }


    private void onBreweriesLoaded(List<Brewery> breweries){
        breweriesView.renderBreweries(breweries);
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
