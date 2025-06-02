package ru.nnov.app.ui.bucket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.nnov.app.databinding.FragmentBucketBinding;

public class BucketFragment extends Fragment {

    private FragmentBucketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BucketViewModel bucketViewModel =
                new ViewModelProvider(this).get(BucketViewModel.class);

        binding = FragmentBucketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textBucket;
        bucketViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}