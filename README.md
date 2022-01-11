# Android Study Jams

Orderring System App

Problem Statement:

Buiness nowadays have got a lot of competition. So it is essential for any buisness owner to give full facilities to retailer. My father has a Company Evershine Epoxy Grout and I have noticed n number of times that all the retailers send the order either by whatsapp or call. Now, you would say why do we need this kind of App. The reason is simple Time Consumption. I have observed on phone call the retailer can't convey the message properly due to connection issues or phone problem etc. So, this app solves the problem for the people. No doubt big MNCs have already solved their problem but what about Small Buisness people. There is either less awareness or they can't afford to pay the money to the app developer company or a freelancer developers. On the ground level in a country like India there also more problems like lack education, thinking.


Solution to the Problem: 
So when I was going through the CodeLabs, I saw the Cupcake Order App.I just loved the UI and Navigation was smooth and simple enough that the unliterated people can also understand the working of how to use it. This has simple features but they can be made more advanced and still keeping the UI simple. Also we can always add support for multiple languages, as in big country like India there 22 different languages which are used in daily life. So this App is just like Cupcake App but little more advanced version.

Funcitonality and Concepts:
This app starts with Google Login using Firebase Authentication System. I just love Firebase, one of the favourite things which I regularly love to use as an Android Developer. After the login is successful, the user will be redirected to HomePage. The HomePage consists of three buttons which 

1. Place an Order:- It redirects to order placing system like the Cupcake App. I have modified the options according to my personal use (For Evershine). I have not implemented functionality for multiple orders like for example if the retailer wants to order more than one Epoxy Items then he will have to go through app many times. Which will increase time consumption istead of reducing it. Check Future scope the changes which I want to implement.

2. View orders:- This will view previous orders made by the user. It shows all the details and Date. This is by the use of Room Database(*).

3. Your Info(User Info):- This page shows the user details which is provided by Google Firebase. It contains Profile Picture of the user, Email id and Name. The user details are also stored on Firestore DataBase. I have Attached Screenshot.

This was all about functionality now let's talk about concepts used. I used Vivedata (mutable), Viewmodel, View Binding, Firebase, Room DataBase, FireStore, Navigation Library, Constraint Layouts.

Future Implementations:
Earlier I talked about the app, it is right now time consuming people who want to order more than one thing at a time. That's where the future implementations come into picture. Actually I was not able to take out time from starting as I was involved in the Internship with a company. This App was made by me in time of 5-6hrs. Now what all things I want to add.
1. The functionality of adding more than one items in sigle order
2. The order summary will also the user details who send the order (just for confirmation)
3. View order Details I want to remove Room and instead get the data from firestore database.
4. Also want to add profile page where user can edit his details.
5. Instead of Simple layout, I want to implement Side drawer Navigation view. 
6. Add Company or Buisness Details like Payment Info, About Us, Contact Us.
7. This is user end app, I want to make Company side app too in which the company will get the order details instead user sending it through whatsapp. (Not Sure for this implementation it is just an idea)
8. I will continue modfying this app till it is perfect. The UI I want to make it a bit stylish using Materials components provided by Google.


* *Help

I was not able to add data in Room Database. If possible please comment on changes needed.
Please help I am not able to add data to Room DataBase using the google codelab code

https://github.com/google-developer-training/android-basics-kotlin-inventory-app/tree/room

Here is the link referral link.

In SummaryFragment.kt in 26th line it throws an Error.


#Thank You 
# Open for Suggestions
