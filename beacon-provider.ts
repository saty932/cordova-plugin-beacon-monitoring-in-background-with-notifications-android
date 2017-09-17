import { Injectable } from '@angular/core';
import { Platform, Events } from 'ionic-angular';
import { IBeacon } from '@ionic-native/ibeacon';



/*
Generated class for the BeaconProvider provider.

See https://angular.io/docs/ts/latest/guide/dependency-injection.html
for more info on providers and Angular 2 DI.
	*/
@Injectable()
export class BeaconProvider {

	delegate: any;
	region: any;

	constructor(public platform: Platform, public events: Events,public IBeacon:IBeacon,public appService:AppService) {
	}

	initialise(): any {
		let promise = new Promise((resolve, reject) => {
			// we need to be running on a device
			if (this.platform.is('cordova')) {

				// Request permission to use location on iOS
				this.IBeacon.requestAlwaysAuthorization();	

				// create a new delegate and register it with the native layer
				this.delegate = this.IBeacon.Delegate();


				// Subscribe to some of the delegate's event handlers
				this.delegate.didRangeBeaconsInRegion()
				.subscribe(
					data => {
						this.events.publish('this.delegate.didRangeBeaconsInRegion', data);
					},
					error => console.error()
					);
				
				// Subscribe to some of the delegate's event handlers
				this.delegate.didEnterRegion()
				.subscribe(
					data => {
						this.events.publish('this.delegate.didEnterRegion', data);
					},
					error => console.error()
					);
				this.delegate.didExitRegion()
				.subscribe(
					data => {
						this.events.publish('this.delegate.didExitRegion', data);
					},
					error => console.error()
					);
				

				
				

				// setup a beacon region – CHANGE THIS TO YOUR OWN UUID
				this.region = this.IBeacon.BeaconRegion('deskBeacon','F7826DA6-ASDF-ASDF-8024-BC5B71E0893E');
				this.region.NotifyEntryStateOnDisplay = true;
				this.region.NotifyOnEntry = true;
				this.region.NotifyOnExit = true;

				// start ranging
				this.IBeacon.startMonitoringForRegion(this.region)
				.then(
					() => {
						resolve(true);
						console.log("monitor for region");
					},
					error => {

						resolve(false);
					}
					);


				this.IBeacon.startRangingBeaconsInRegion(this.region)
				.then(
					() => {
						resolve(true);
					},
					error => {

						resolve(false);
					}
					);


			} else {

				resolve(false);
			}
		}
		);

		return promise;
	}
}


