import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.banco_jofago.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        val languagePreference = findPreference<ListPreference>("language_preference")
        languagePreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()


        val dataSourcePreference = findPreference<ListPreference>("data_source_preference")
        dataSourcePreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
    }
}
