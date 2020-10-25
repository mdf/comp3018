// IStringReverser.aidl
package com.example.pszmdf.aidlservice;

interface IStringReverser {

    String reverseString(in String inString);

    int modifyBundle(in Bundle bundle);

    int modifyBundleReference(inout Bundle bundle);
}
