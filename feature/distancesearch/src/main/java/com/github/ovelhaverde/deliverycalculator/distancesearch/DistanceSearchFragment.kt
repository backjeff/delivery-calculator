package com.github.ovelhaverde.deliverycalculator.distancesearch

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import com.github.ovelhaverde.deliverycalculator.distancesearch.databinding.FragmentDistanceSearchBinding
import com.github.overlhaverde.deliverycalculator.feature.base.BaseFragment
import com.github.overlhaverde.deliverycalculator.feature.extensions.setupViewAsMoney
import org.koin.androidx.viewmodel.ext.android.viewModel

class DistanceSearchFragment
    : BaseFragment<FragmentDistanceSearchBinding>(R.layout.fragment_distance_search) {

    private val viewModel: DistanceSearchViewModel by viewModel()

    override fun addObservers(owner: LifecycleOwner) {
        with(viewModel) {
            distanceSearch.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { distanceSearch ->
                    viewModel.getPriceFormatted(
                        kmPrice = binding.kmPrice.text.toString(),
                        distanceSearch = distanceSearch
                    )
                }
            )

            price.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { response ->
                    binding.distanceResponse.text = response.distance
                    binding.valueResponse.text = response.value
                    binding.responseLayout.isVisible = true
                }
            )
        }
    }

    override fun setupView() {
        binding.origin.setText("Igarapé MG")
        binding.destination.setText("Betim MG")
        binding.kmPrice.setText("R$ 1.40")
        binding.kmPrice.setupViewAsMoney()

        binding.searchButton.setOnClickListener {
            viewModel.getDistance(
                origin = binding.origin.text.toString(),
                destination = binding.destination.text.toString(),
            )
        }
    }

    override fun setRootBinding() = FragmentDistanceSearchBinding.inflate(layoutInflater)

    override fun startLoading() {
        binding.responseLayout.isVisible = false
    }

    override fun onViewStateError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

}