#include <iostream>
#include <wiringPi.h>
#include <unistd.h>
using namespace std;
#define LED_GPIO 17 // this is GPIO17, Pin 11
#define BUTTON_GPIO 27 // this is GPIO27, Pin 13
// the Interrupt Service Routine (ISR) to light the LED
int a = 0;
void count(void) {
	static int x = 0;
	cout << "Button pressed " << endl;
	x++;
}
int main() { // must be run as root
	wiringPiSetupGpio(); // use the GPIO numbering
	pinMode(LED_GPIO, OUTPUT); // the LED
	pinMode(BUTTON_GPIO, INPUT); // the Button
	digitalWrite(LED_GPIO, LOW); // LED is off
	cout << "Press the button on GPIO " << BUTTON_GPIO << endl;
	// call the lightLED() ISR on the rising edge (i.e., button press)
	wiringPiISR(BUTTON_GPIO, INT_EDGE_RISING, &press);
	int i = 0
		whiie(1) {
		if (i == 5) {
			cout << x << endl;
			i = 0;
		}
		if (x == 10)
			break;
		i++;
		sleep(1)
	}
	return 0; // program ends after 10s
}