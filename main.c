#include <stdio.h>
#include <wiringPi.h>

#define Button 24

int buttonStatus;
int oneTimeFlag;
int main() {
	wiringPiSetUp();
	int count = 0;
	pinMode(Button, INPUT);
	while (1) {
		if (digitalRead(Button) == HIGH) {
			if (oneTimeFlag == 0) {
				oneTimeFlag = 1;
				buttonStatus != buttonStatus;
				printf("%d", count + 1);
			}
		}
		else
			oneTimeFlag = 0;
	}
}