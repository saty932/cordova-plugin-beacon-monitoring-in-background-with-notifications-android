# cordova-plugin-beacon-monitoring-in-background-with-notifications-android

This repository is for getting notifications from background when the app is closed in ionic app(Android only).



This is the plugin is combination of two plugings

https://github.com/lulibrary/Background-Beacon-Monitoring
https://github.com/petermetz/cordova-plugin-ibeacon

Thanks to the authors both the above plugins using this plugins we have developed this functionality.


Step1:

$ ionic cordova plugin add cordova-plugin-ibeacon
$ npm install --save @ionic-native/ibeacon

Using above commands download the ibeacon plugin

Add the below code for ibeacon usage in ionic app

Create one provider and copy the all code shown below

import { IBeacon } from '@ionic-native/ibeacon';

constructor(private ibeacon: IBeacon) { }

// Request permission to use location on iOS
this.ibeacon.requestAlwaysAuthorization();
// create a new delegate and register it with the native layer
let delegate = this.ibeacon.Delegate();


// Subscribe to some of the delegate's event handlers
delegate.didRangeBeaconsInRegion()
.subscribe(
data => console.log('didRangeBeaconsInRegion: ', data),
error => console.error();
);


delegate.didStartMonitoringForRegion()
.subscribe(
data => console.log('didStartMonitoringForRegion: ', data),
error => console.error();
);


delegate.didEnterRegion()
.subscribe(
data => {
console.log('didEnterRegion: ', data);
}
);


Replace the beacon uuid with your beacon uuid and identifier also

let beaconRegion = this.ibeacon.BeaconRegion('deskBeacon','F7826DA6-ASDF-ASDF-8024-BC5B71E0893E');

//this below methods for didenterregion and didexit regions methods intialization

this.ibeacon.startMonitoringForRegion(beaconRegion)
.then(
() => console.log('Native layer recieved the request to monitoring'),
error => console.error('Native layer failed to begin monitoring: ', error)
);


//this below methods for initialize didrangebeaconsinregion method

this.IBeacon.startRangingBeaconsInRegion(this.region)
.then(
() => {
resolve(true);
},
error => {

resolve(false);
}
);

If you have any doubt regarding creation of provider we have added beacon-provided.ts file it's contains the all the methods shown above
just copy the file beacon-provide.ts into your provides and call the method initialize using beaconprovider so it will start monitoring beacons
before that make sure you have replaced beacon detailes with your beacons.


step2:

with in our repository we have developed one plugin using

https://github.com/lulibrary/Background-Beacon-Monitoring

that is Background-Beacon-Monitoring-master plugin

Download this folder into your app folder

using the below commands you can add this plugin to your app

cordova plugin add "Background-Beacon-Monitoring"

After succesfully added you can see this plugin in plugins as

uk.ac.lancaster.library.backgroundbeacons


using the below code we can use this plugin in ionic app

In you app folder with in app.components.ts file

After all imports declare the variable defined as below

declare var BackgroundBeaconMonitoring:any;

with in the contoller we are accesing the all methods here


BackgroundBeaconMonitoring.requestPermissions();

//pass whatever the parameters you want as shown below

BackgroundBeaconMonitoring.startService("BeaconService","bottulasathishbr.com","Ab1010","https//www.avantasy.com","v2",true,function(){
console.log("sucess");
},function(err){
console.log("error");
});

BackgroundBeaconMonitoring.startMonitoringRegion("deskbeacon","F7826DA6-ASDF-ASDF-8024-BC5B71E0893E",null,null);

BackgroundBeaconMonitoring.startRangingRegion("deskbeacon","F7826DA6-ASDF-ASDF-8024-BC5B71E0893E",null,null);

After doing this this plugin is able to run all the beacons methods even when the app is closed


step3:

Now with in this repository you will see a folder called 
com.unarin.cordova.beacon

copy the src/android/Config.java and src/android/LocationManager.java and plugin.xml Files into your cordova plugin com.unarin.cordova.beacon


Now you are able to get notify when the app is closed in ionic android app

Make sure that with Config.java you have given same package name what you are using now 

getNotificationIntentPackageName()
using the above method you can change the package name
replace io.ionic.starter with your package name


with in the config.xml in your app folder you can see the package name of your app

it should be like

<widget id="com.ionicframework.appname" version="0.0.1" xmlns="http://www.w3.org/ns/widgets" xmlns:cdv="http://cordova.apache.org/ns/1.0"> 


	public String getNotificationContentTitle() {
		return getString(PREFIX + CAT_NOTIFY + "ContentTitle", "PromoNear");
	}

	public String getNotificationContentText() {
		return getString(PREFIX + CAT_NOTIFY + "ContentText", "Are You In Beacon Region");
	}

	public String getNotificationIntentPackageName() {
		return getString(PREFIX + CAT_NOTIFY + "Intent.packageName", "io.ionic.starter");
	}

now type the command 

npm install --save @ionic-native/ibeacon

you will be notified when you enter into the any beacon region 

if you want to notify didexit and didrangebeaconsinregion then again goto LocationManager.java find the methods add the line ShowNotification();



Thanks for the authors who created thos plugins becuase those plugins we are able to create this functionality.	





