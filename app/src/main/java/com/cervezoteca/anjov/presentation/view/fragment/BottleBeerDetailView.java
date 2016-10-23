package com.cervezoteca.anjov.presentation.view.fragment;

import com.cervezoteca.anjov.domain.model.BottleBeer;
import com.cervezoteca.anjov.presentation.view.LoadDataView;

import java.util.List;

/**
 * Created by anjov on 07/10/2016.
 */

public interface BottleBeerDetailView extends LoadDataView {
    void renderBottleBeer(BottleBeer beerList);
}
