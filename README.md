# ITunesSong
iTunes에 있는 Greenday 음악리스트 애플리케이션

<br>

[![Build Status](https://travis-ci.com/sysout-achieve/ITunesSong.svg?branch=master)](https://travis-ci.com/sysout-achieve/ITunesSong)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
<br>

### 구성 : <br>
- Language : Kotlin <br>
- DI : Hilt <br>
- AAC(MVVM, DataBinding, LiveData, Room, Navigation, Paging) <br>
- Opensource: RxKotlin, Retrofit, Glide, Swiperefreshlayout <br>
- Test : Unit Test(JUnit4)
- TravisCI

<hr>

## 소개
과제에서 주어진 요구사항을 통해 API통신과 내부 DB를 함께 사용해야함을 확인했습니다.<br>
확인한 사항을 효과적으로 처리하기 위해 Repository Pattern에 대한 고민을 많이하여 작성하였습니다.<br>
RepositoryPattern을 이용하여 현재는 내부DB에 저장하고 있지만 서버로 저장소를 수정 및 확장하게 될 경우 Repository의 변경만으로 의도하는 바를 이룰 수 있습니다.

### DI(Hilt)

: di를 적용하여 클래스 간 의존성을 줄여 설계하였습니다. <br>
의존성 주입 Framework를 이용하여 의존성을 주입할 객체의 생명주기 관리가 쉬워집니다.<br>
주입한 의존성의 변경이 주입된 객체에게 영향을 주지 않는 설계가 가능해집니다.<br>

짧은 시간동안 짜임새있는 논리적인 코드 고민에 더 많은 시간을 할애하기 위해 Framework는 제가 가장 많이 사용하는 Hilt를 사용했습니다. 
 

## AAC

### MVVM
: Jetpack AAC와 함께 사용하기에 적합한 Architecture <br>

- layer를 나눠서 각 계층이 가진 역할을 명확하게 합니다.
- layer의 계층별 역할을 명확하게 하는 것은 통일성 있는 코드를 만들 수 있습니다.
- Activity, Fragment내부의 메소드명을 화면에서 이루어지는 행위 위주의 메소드명으로 짠다면 layer를 더 명확하게 나눌 수 있습니다. 
- jetpack의 AAC에 포함된 library들이 MVVM Architecture에서 효과적으로 활용됩니다.

__DataBinding__ <br>
: xml에 데이터를 바인딩하여 데이터 객체와 뷰컴포넌트간 데이터 전달이 가능합니다.<br>
뷰컴포넌트를 binding객체에서 바로 찾아서 사용할 수 있습니다.<br>
- viewModel에 ViewComponent 코드를 차단 가능하게 합니다.
- findViewById 코드가 사라져 소스가 깔끔해집니다.


__LiveDate__ <br>
: Activity, Fragment에서 binding되어진 viewModel의 데이터를 observing합니다.
- 바인딩한 viewmodel의 데이터를 간접적으로 사용 가능하게하여 데이터와 뷰의 분리 실현시킬 수 있습니다.

### ViewModel(AAC)
: 화면 생명주기를 감지하고 있는 독립된 생명주기를 갖습니다. <br>
이를 통해 화면의 회전으로인한 데이터 유실을 막을 수 있습니다.

### Room
: ORM 기반의 Database를 이용하여 객체 지향적 사고로 DB를 사용하고 싶었습니다.<br>
설계에 어려움이 있다는 단점은 주어진 과제를 기준으로 할 때 단점이라 여기기 어려웠고, <br>
적용했을 때 실제 Data를 가진 객체처럼 DB사용이 가능했습니다.<br>

### Paging
: Jetpack AAC에서 제공하는 paging library를 이용하였습니다.<br>
recyclerView에서 적용하기 위해 만들어진 라이브러리로 간편한 적용과 직관적인 처리가 가능하다는 강점이 있었습니다.<br>

paging처리를 SongsFragment와 FavoritesFragment 모두 적용하였습니다.<br>
두 화면의 RecyclerView에서 표현해야할 항목들만 다를 뿐, 같은 기능을 하고 있다 판단하여 하나의 Adapter와 Paging class를 양쪽에서 사용할 수 있도록 추상화하여 구현하였습니다. 

### Glide
: 대표적인 이미지 처리 라이브러리 중에 Glide를 선택했습니다.<br>
외부에서 참고할 수 있는 벤치마크 자료를 통해 화면에 그리는 속도에 최적화된 라이브러리가 Glide임을 확인했습니다.<br>
과제에서 요구하는 애플리케이션의 목적이 사용되는 UI이미지 품질보다 __사용성의 중요도가 더 높다 판단__ 하여 Glide를 결정했습니다.

## 추가 고려사항
### 1. 확장성을 위한 고려사항
: __Domain model과 Dto, Entity를 각각 만들어 사용하였습니다.__ <br>
각 모델의 내부데이터를 모두 같은 것으로 작성할 수 있지만, 객체의 역할과 책임이 다르기 때문에 새롭게 만들어 사용하였습니다.<br>
이를 통해 외부에서 내려주는 데이터의 모양이 변경되어도 mapper만으로 다른 객체들의 변화를 막을 수 있습니다.<br>
또 Entity를 직접 화면에 출력하기 위한 모델로 사용하지 않아 표현하려는 객체의 모양이 변화하여도 DB테이블은 변경점이 없습니다.

### 2. 안정성을 위한 고려사항
: __Repository 중심의 TEST__ <br>
주어진 과제에서 Favorite Track 저장을 핵심 기능이라 판단하여 Test를 작성했습니다. <br>
Favorite Track 저장을 위한 Repository의 저장 및 호출 기능 위주의 test code를 작성하여 기능을 보장할 수 있었습니다.

### 3. 성능을 위한 고려사항
: __DB에서 가져온 전체 Favorite 트랙의 ID를 HashMap으로 관리합니다.__<br>
처음 DB에서 호출하여 HashMap에 저장할 때(앱 실행 시 한 번) __O(n)__ 의 시간복잡도를 사용합니다.<br>
이후 RecyclerView에서 Favorite(별모양 토글버튼)을 결정할 때는 __O(1)__ 의 시간복잡도를 기대할 수 있습니다.<br>
외부에서 객체를 불러오는 시점에 DB에서 체크하는 로직을 사용하려 했으나 페이징 처리가 들어있고 호출하는 갯수의 크기에 따라 시간복잡도가 커지는 상황을 방지하고 싶었습니다. <br>
RecyclerView에서의 binding 시 연산은 사용성에 크게 영향을 줄 수 있는 부분이기 때문에 시간복잡도를 최대한 줄일 수 있는 방안으로 선택했습니다.<br>
이를 통해 같은 화면에서 상대적으로 큰 리소스를 필요로하는 상황이 생겨도 Frame이슈에 대비할 수 있게 됩니다.

### 4. 협업을 위한 고려사항
: __코드에 상수 하드코딩을 지양했습니다.__<br>
로직안에서의 상수는 협업 시 상수 변경에 있어 사이드 이펙을 고려하기 어려워진다고 생각했습니다. <br>
목적이 있는 상수를 목적을 알 수 있는 변수명과 함께 선언하여, 사용 목적을 분명하게 만들었습니다.



<hr>

### 시행착오

minSdkTarget이 19 의 애플리케이션을 만들면서 DB접근을 coroutine을 사용했었습니다. (coroutine branch로 작업본 남김) <br>
개발완료 후 테스트 코드 작성 단계에서 21버전 이하의 안드로이드 단말에서 간헐적 crash 이슈가 발생함을 확인하였고, <br> 
Rx로 비동기처리를 변환 적용하였습니다.

이러한 시행착오를 쉽게 넘기지 않아야겠습니다. <br>
기술을 사용할 때 꼼꼼하게 확인하고 적용하는 것에 다시 한 번 경각심을 일깨워주었습니다.
