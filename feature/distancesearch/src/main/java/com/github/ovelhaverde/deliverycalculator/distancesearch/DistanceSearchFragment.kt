package com.github.ovelhaverde.deliverycalculator.distancesearch

import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import com.github.ovelhaverde.deliverycalculator.distancesearch.databinding.FragmentDistanceSearchBinding
import com.github.overlhaverde.deliverycalculator.feature.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DistanceSearchFragment
    : BaseFragment<FragmentDistanceSearchBinding>(R.layout.fragment_distance_search) {

    private val viewModel: DistanceSearchViewModel by viewModel()

    override fun addObservers(owner: LifecycleOwner) {
        with(viewModel) {
            distanceSearchFormData.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { formData ->
                    binding.origin.text = formData.origin
                    binding.destination.text = formData.destination
                    binding.kmPrice.text = formData.kmPrice
                    binding.kmPrice.setupViewAsMoney()
                    binding.roundDistanceSwitch.isChecked = formData.roundDistance
                },
                onComplete = { setupFormTextChangedListener() }
            )

            distanceSearch.onPostValue(viewLifecycleOwner)

            price.onPostValue(
                lifecycleOwner = viewLifecycleOwner,
                onSuccess = { response ->
                    binding.distanceResponse.text = response.distance
                    binding.durationResponse.text = response.duration
                    binding.valueResponse.text = response.value
                    binding.responseLayout.isVisible = true
                    binding.searchButton.isLoading = false
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
                origin = binding.origin.text,
                destination = binding.destination.text,
                kmPrice = binding.kmPrice.text,
                shouldRoundDistance = binding.roundDistanceSwitch.isChecked
            )
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setDarkMode(isChecked)
        }
    }

    private fun setupFormTextChangedListener() {
        binding.origin.editText.addTextChangedListener { viewModel.setOrigin(it.toString()) }
        binding.destination.editText.addTextChangedListener { viewModel.setDestination(it.toString()) }
        binding.roundDistanceSwitch.setOnCheckedChangeListener { _, isChecked -> viewModel.setRoundDistance(isChecked) }
        binding.kmPrice.run {
            setOnKeyListener { _, _, _ ->
                viewModel.setKmPrice(binding.kmPrice.text)
                false
            }
            editText.addTextChangedListener { viewModel.setKmPrice(it.toString()) }
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

    override fun onViewStateError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

}