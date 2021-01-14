package com.finwin.doorstep.rightviewfinance.login


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.finwin.doorstep.rightviewfinance.R
import com.finwin.doorstep.rightviewfinance.databinding.ActivityLoginBinding
import com.finwin.doorstep.rightviewfinance.home.home_activity.HomeActivity
import com.finwin.doorstep.rightviewfinance.login.action.LoginAction
import com.finwin.doorstep.rightviewfinance.utils.Constants
import com.finwin.doorstep.rightviewfinance.utils.DataHolder
import com.finwin.doorstep.rightviewfinance.utils.VersionChecker
import java.util.concurrent.ExecutionException

class LoginActivity : AppCompatActivity() {
    lateinit var viewmodel: LoginViewmodel
    lateinit var binding: ActivityLoginBinding
    var latestVersion: String = ""
    var currentVersion:String = ""
   companion object{
       lateinit var sharedPreferences: SharedPreferences
       lateinit var editor: SharedPreferences.Editor
   }
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel=ViewModelProvider(this).get(LoginViewmodel::class.java)
        binding.viewmodel=viewmodel

        sharedPreferences =getSharedPreferences(Constants.PREFERENCE_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()


       // checkVersion()
        viewmodel.mAction.observe(this, Observer {


            when (it.action) {
                LoginAction.LOGIN_SUCCESS -> {


                    editor.putString(Constants.BRANCH_ID, it.loginResponse.user.data.BRANCH_ID)
                    editor.putString(Constants.BRANCH_NAME, it.loginResponse.user.data.BRANCH_NAME)
                    editor.putString(Constants.USER_ID, it.loginResponse.user.data.USER_ID)
                    editor.putString(Constants.USER_NAME, it.loginResponse.user.data.USER_NAME)
                    editor.putString(Constants.AGENT_ID, it.loginResponse.user.data.USER_ID)
                    editor.putString(Constants.AGENT_NAME, it.loginResponse.user.data.USER_NAME)
                    editor.commit()

                    viewmodel.getMasters()

                }

                LoginAction.API_ERROR -> {
                    viewmodel.cancelLoading()
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }

                LoginAction.LOGIN_ERROR -> {
                    viewmodel.cancelLoading()
                    Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                }
                LoginAction.GET_MASTERS_SUCCESS -> {
                    viewmodel.cancelLoading()
                    DataHolder.accountType = it.getMasters.ACC_TYPE.data
                    DataHolder.nameTitleList = it.getMasters.NTITLE.data
                    DataHolder.genderList = it.getMasters.GNDR.data
                    DataHolder.maritalStatusList = it.getMasters.MARSTATUS.data
                    DataHolder.occupationList = it.getMasters.OCCU.data
                    DataHolder.modeOperation = it.getMasters.MODE_OPER.data
                    DataHolder.constitution = it.getMasters.CONSTITUTION.data
                    DataHolder.branchdetails = it.getMasters.BRANCH_DETAILS.data
                    DataHolder.accountCtg = it.getMasters.ACC_CTG.data
                    DataHolder.accountRelation = it.getMasters.ACC_RELAT.data

                    val intent: Intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }

    private fun checkVersion() {
        val versionChecker = VersionChecker()
        try {
            latestVersion = versionChecker.execute().get()
            // Toast.makeText(getActivity().getApplicationContext(), latestVersion , Toast.LENGTH_SHORT).show();
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        val manager: PackageManager = packageManager
        var info: PackageInfo? = null
        try {
            info = manager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        assert(info != null)
        currentVersion = info!!.versionName

        if (currentVersion.toFloat() < latestVersion.toFloat()) {
            showUpdateDialog()

        }
    }

    private fun showUpdateDialog() {
        val alertDialog2 = AlertDialog.Builder(this)
        // Setting Dialog Title
        alertDialog2.setTitle("Update Available")
        // Setting Dialog Message
        alertDialog2.setMessage("There is a newer version of this application is available")
        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton(
            "Update"
        ) { dialog, which -> // Write your code here to execute after dialog
            val i = Intent(Intent.ACTION_VIEW)
            i.data =
                Uri.parse("https://play.google.com/store/apps/details?id=com.finwin.doorstep.rightviewfinance")
            startActivity(i)
        }
        alertDialog2.setCancelable(false)
        alertDialog2.show()
    }

    override fun onResume() {
        super.onResume()

    }

}