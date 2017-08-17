package cm.it.secret.gasmyr.toolkit.util;

import android.Manifest;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cm.it.secret.gasmyr.toolkit.core.GreetingPerson;

/**
 * Created by gasmyr.mougang on 5/30/17.
 */

public class PhoneUtils {
    public static boolean isMtn(String number) {
        return (number.startsWith("67") || number.startsWith("654"));
    }

    public static boolean isOrange(String number) {
        return (number.startsWith("69") || number.startsWith("656"));
    }

    public static boolean isPhone(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_NONE;
    }

    public static boolean isNexttel(String number) {
        return (number.startsWith("66"));
    }

    public static void sendNewSms(@NonNull String message, String number) {
        if (Utils.isEmpty(message)) return;
        if (Utils.isEmpty(number)) return;
        SmsManager smsManager = SmsManager.getDefault();
        if (message.length() > 140) {
            ArrayList<String> messageParts = smsManager.divideMessage(message);
            smsManager.sendMultipartTextMessage(number, null, messageParts, null, null);
        } else {
            smsManager.sendTextMessage(number, null, message, null, null);
        }
    }

    public static void sendSmsSilent(String phoneNumber, String message, Context context) {
        if (Utils.isEmpty(message)) return;
        PendingIntent sentIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
        SmsManager smsManager = SmsManager.getDefault();
        if (message.length() >= 70) {
            List<String> ms = smsManager.divideMessage(message);
            for (String str : ms) {
                smsManager.sendTextMessage(phoneNumber, null, str, sentIntent, null);
            }
        } else {
            smsManager.sendTextMessage(phoneNumber, null, message, sentIntent, null);
        }
    }

    public static String getContactName(@NonNull Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME}, null, null, null);
        if (cursor == null) {
            return phoneNumber;
        }
        String contactName = phoneNumber;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return contactName;
    }

    public static boolean canSendSmsInPromisiousMode() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT;
    }

    public static boolean isContactsPermissionIsGranted(@NonNull Context context) {
        int permissionCheck = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_CONTACTS);
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    @NonNull
    public static List<GreetingPerson> getOrangePersonToGreet(@NonNull Context context) {
        List<GreetingPerson> persons = new ArrayList<>();
        GreetingPerson person;
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (PhoneUtils.isOrange(phoneNumber)) {
                person = new GreetingPerson(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        phoneNumber);
                persons.add(person);
            }
        }
        cursor.close();
        return persons;
    }

    @NonNull
    public static List<GreetingPerson> getMtnPersonToGreet(@NonNull Context context) {
        List<GreetingPerson> persons = new ArrayList<>();
        GreetingPerson person;
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (PhoneUtils.isMtn(phoneNumber)) {
                person = new GreetingPerson(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        phoneNumber);
                persons.add(person);
            }
        }
        cursor.close();
        return persons;
    }

    @NonNull
    public static List<GreetingPerson> getAllContact(@NonNull Context context) {
        Set<GreetingPerson> persons = new HashSet<>();
        GreetingPerson person;
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            person = new GreetingPerson(cursor.getString(
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            persons.add(person);
        }
        cursor.close();
        return new ArrayList<>(persons);
    }

}
