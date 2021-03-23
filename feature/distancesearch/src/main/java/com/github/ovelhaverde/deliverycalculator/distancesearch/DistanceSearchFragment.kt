package com.github.ovelhaverde.deliverycalculator.distancesearch

import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
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
            distanceSearchFormData.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { formData ->
                    binding.origin.setText(formData.origin)
                    binding.destination.setText(formData.destination)
                    binding.kmPrice.setText(formData.kmPrice)
                    binding.kmPrice.setupViewAsMoney()
                },
                onComplete = { setupFormTextChangedListener() }
            )

            distanceSearch.onPostValue(viewLifecycleOwner)

            price.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { response ->
                    binding.distanceResponse.text = response.distance
                    binding.valueResponse.text = response.value
                    binding.responseLayout.isVisible = true
                }
            )

            darkMode.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { enableDarkMode(it) }
            )
        }
    }

    override fun setupView() {

        binding.searchButton.setOnClickListener {
            viewModel.getPrice(
                origin = binding.origin.text.toString(),
                destination = binding.destination.text.toString(),
                kmPrice = binding.kmPrice.text.toString(),
            )
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }
    }

    private fun setupFormTextChangedListener() {
        binding.origin.addTextChangedListener { viewModel.setOrigin(it.toString()) }
        binding.destination.addTextChangedListener { viewModel.setDestination(it.toString()) }
        binding.kmPrice.run {
            setOnKeyListener { _, _, _ ->
                viewModel.setKmPrice(binding.kmPrice.text.toString())
                false
            }
//            addTextChangedListener { viewModel.setKmPrice(it.toString()) }
            setupViewAsMoney()
        }
    }

    private fun enableDarkMode(enabled: Boolean) {
        binding.darkModeSwitch.isChecked = enabled
        when (enabled) {
            true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
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