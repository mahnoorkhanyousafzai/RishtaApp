package com.example.rishtaapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class CreateClientFragment extends Fragment {

    private Spinner spinnerStatus, martialstatus, Sgender;
    private ImageView imageViewProfilePicture;
    private Uri imageUri;
    String imageurl;
    int SELECT_PICTURE = 200;
    private static final int GALLERY_REQUEST = 1;  // Request code for gallery
    private static final int STORAGE_PERMISSION_CODE = 2;  // Request code for permission
    private DBHelper dbHelper;
    private EditText etFullname, etAge, etHeight, etCast, etReligion, etSect, etEducation,
            etHobbies, etFamilyDetails, etResidence, etProfession, etJob,
            etComplexion, etChildren, etMindset, etReferBy, etRequirement,
            etCustomerFee, etContactDetails,eAfterRishtaAmount;
    private Spinner spGender, spMaritalStatus, spStatus;
    private Button btnSubmitForm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_client, container, false);

        spinnerStatus = view.findViewById(R.id.spinnerStatus);
        martialstatus = view.findViewById(R.id.spinnerMaritalStatus);
        Sgender = view.findViewById(R.id.spinnerGender);
        imageViewProfilePicture = view.findViewById(R.id.imageViewProfilePicture);
        Button buttonUploadImage = view.findViewById(R.id.buttonUploadImage);
        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize DBHelper
        dbHelper = new DBHelper(getActivity());

        // Initialize EditText fields
        etFullname = view.findViewById(R.id.editTextFullname);
        etAge = view.findViewById(R.id.editTextAge);
        etHeight = view.findViewById(R.id.editTextHeight);
        etCast = view.findViewById(R.id.editTextCast);
        etReligion = view.findViewById(R.id.editTextReligion);
        etSect = view.findViewById(R.id.editTextSect);
        etEducation = view.findViewById(R.id.editTextEducation);
        etHobbies = view.findViewById(R.id.editTextHobbies);
        etFamilyDetails = view.findViewById(R.id.editTextFamilyDetails);
        etResidence = view.findViewById(R.id.editTextResidence);
        etProfession = view.findViewById(R.id.editTextProfession);
        etJob = view.findViewById(R.id.editTextJob);
        etComplexion = view.findViewById(R.id.editTextComplexion);
        etChildren = view.findViewById(R.id.editTextChildren);
        etMindset = view.findViewById(R.id.editTextMindset);
        etReferBy = view.findViewById(R.id.editTextReferBy);
        etRequirement = view.findViewById(R.id.editTextRequirement);
        etCustomerFee = view.findViewById(R.id.editTextCustomerFee);
        etContactDetails = view.findViewById(R.id.editTextContactDetails);
        eAfterRishtaAmount = view.findViewById(R.id.edittextAfterRishtaAmount);

        // Initialize Spinners
        spGender = view.findViewById(R.id.spinnerGender);
        spMaritalStatus = view.findViewById(R.id.spinnerMaritalStatus);
        spStatus = view.findViewById(R.id.spinnerStatus);

        // Initialize Submit Button
        btnSubmitForm = view.findViewById(R.id.btnSubmitForm);

        btnSubmitForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input values
                String fullname = etFullname.getText().toString().trim();
                String gender = spGender.getSelectedItem().toString();
                String ageStr = etAge.getText().toString().trim();
                String height = etHeight.getText().toString().trim();
                String cast = etCast.getText().toString().trim();
                String religion = etReligion.getText().toString().trim();
                String sect = etSect.getText().toString().trim();
                String education = etEducation.getText().toString().trim();
                String hobbies = etHobbies.getText().toString().trim();
                String familyDetails = etFamilyDetails.getText().toString().trim();
                String maritalStatus = spMaritalStatus.getSelectedItem().toString();
                String residence = etResidence.getText().toString().trim();
                String profession = etProfession.getText().toString().trim();
                String job = etJob.getText().toString().trim();
                String complexion = etComplexion.getText().toString().trim();
                String children = etChildren.getText().toString().trim();
                String mindset = etMindset.getText().toString().trim();
                String referBy = etReferBy.getText().toString().trim();
                String status = spStatus.getSelectedItem().toString();
                String requirement = etRequirement.getText().toString().trim();
                String customerFeeStr = etCustomerFee.getText().toString().trim();
                String contactDetails = etContactDetails.getText().toString().trim();
                String afterrishtamount = eAfterRishtaAmount.getText().toString().trim();

                // Validate inputs
                if (fullname.isEmpty() || ageStr.isEmpty() || height.isEmpty() || cast.isEmpty() ||
                        religion.isEmpty() || sect.isEmpty() || education.isEmpty() || hobbies.isEmpty() ||
                        familyDetails.isEmpty() || residence.isEmpty() || profession.isEmpty() ||
                        job.isEmpty() || complexion.isEmpty() || children.isEmpty() || mindset.isEmpty() ||
                        referBy.isEmpty() || requirement.isEmpty() || customerFeeStr.isEmpty() || contactDetails.isEmpty() ||
                afterrishtamount.isEmpty()) {

                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert age and customerFee to proper types
                int age;
                double customerFee;
                double afterrishtaFee;
                try {
                    age = Integer.parseInt(ageStr);
                    customerFee = Double.parseDouble(customerFeeStr);
                    afterrishtaFee = Double.parseDouble(afterrishtamount);
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Invalid number format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Insert data into database
                boolean result = dbHelper.insertClient(fullname, gender, age, height, cast, religion, sect,
                        education, hobbies, familyDetails, maritalStatus, residence,imageurl, profession, job,
                        complexion, children, mindset, referBy, status, requirement, customerFee, afterrishtaFee,contactDetails);

                // Show result toast
                if (result) {
                    Toast.makeText(getActivity(), "Client data inserted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error inserting client data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void imageChooser()
    {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null
                            && data.getData() != null) {
                         imageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            // Use requireActivity() to get the content resolver in a fragment
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(
                                    requireActivity().getContentResolver(),
                                    imageUri);
                            imageViewProfilePicture.setImageBitmap(selectedImageBitmap);
                            imageurl = imageUri.toString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });




}
