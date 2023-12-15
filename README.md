
<img src="https://github.com/oguncan/WorksyCaseStudy/blob/master/untitled.gif" align="right" width="25%"></img>

## 💡 Stack & Libraries

- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- JetPack
  - LiveData -observable data holder class.
  - Lifecycle - Create a UI that automatically responds to lifecycle events.
  - ViewModel - UI related data holder, Easily schedule asynchronous tasks for optimal execution.
- [Dagger-Hilt](https://dagger.dev/hilt/) - dependency injection.
- [Retrofit2](https://github.com/square/retrofit) - REST APIs.
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging web server.
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Lottie](https://github.com/airbnb/lottie-android) - implementing animations

## Features
- Performans işlemi için pagination eklendi.
- ViewModel üzerinden örnek olması açısından Unit test gerçekleştirildi.
- Tasarım ayrıntılarına dikkat edildi.
- Slider için gerekli hesaplamalar gerçekleştirilerek testler gerçekleştirildi.
- Shimmer tasarımlar eklenerek Error, Success ve Loading durumları ele alındı.
- ListView tasarımı küçük kalacağını düşündüğüm için Collapse tasarım tercih edildi.

<p></p>
<p></p>
<p></p>
<p></p>

## Screenshots
<p align="center">
<img src="https://github.com/oguncan/WorksyCaseStudy/blob/master/Screenshot_1702662783.png" width="20%" height="auto">
<img src="https://github.com/oguncan/WorksyCaseStudy/blob/master/Screenshot_1702662790.png" width="20%" height="auto">
</p>

<p align="center">
<img src="https://github.com/oguncan/WorksyCaseStudy/blob/master/Screenshot_1702663021.png" width="20%" height="auto">
<img src="https://github.com/oguncan/WorksyCaseStudy/blob/master/Screenshot_1702662903.png" width="20%" height="auto">
</p>

## Architecture
Resturant is based on MVVM architecture and a repository pattern.

![architecture](https://github.com/oguncan/WorksyCaseStudy/blob/master/77502018-f7d36000-6e9c-11ea-92b0-1097240c8689.png)
