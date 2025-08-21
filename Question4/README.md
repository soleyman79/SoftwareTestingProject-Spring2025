# Question 4

## Section A

Here are the mutants related to the `isEligible` method. For brevity, only the changes are listed below:

- Convert `=<` to `<`

``` java
if (creditScore > 700 && income >= 50000 && loanAmount <= 50000) return true;
```

- Convert `&&` to `||`

``` java
if (creditScore >= 650 || income >= 60000 && loanAmount <= 40000 && employmentStable) return true;
```

- Negate the Conditions

``` java
if (!isInputValid(creditScore, income, loanAmount, repaymentMonths)) return true;
```

- Remove a Condition

``` java
if (creditScore >= 650 && loanAmount <= 40000 && employmentStable) return true;
```

- Convert `&&` to `||`

``` java
else if (creditScore < 600 || !hasGuarantor) {
    return "REJECTED_LOW_CREDIT";
}
```

- Convert `>` to `>=`

``` java
else if (creditScore <= 600 && !hasGuarantor) {
    return "REJECTED_LOW_CREDIT";
}
```

- Convert `=>` to `>`

``` java
if (loanAmount < 50000) return true;
```

- Eliminate a Statement

``` java
if (!isInputValid(...)) return false;
```

- Replace relational operator

``` java
return creditScore > 300 && creditScore < 850
    && income >= 0
    && loanAmount > 0
    && repaymentMonths > 0;
```

- Change Condition

``` java
if (creditScore >= 650 && income >= 60000 && loanAmount <= 40000 && !employmentStable) return true;
```

- Change Condition

``` java
if (creditScore >= 600 && !hasGuarantor && repaymentMonths < 24) return true;
```

## Section B

.در ابتدا جدولی از جهش‌های موجود ارائه می‌شود

| جهش شماره | شرح جهش                                                          |
|-----------|-------------------------------------------------------------------|
| M1        | تغییر `creditScore >= 700` به `creditScore > 700`                 |
| M2        | تغییر `&&` به `||` در شرط دوم (در isEligible)                    |
| M3        | معکوس کردن مقدار برگشتی `isInputValid` از false به true          |
| M4        | حذف شرط income از شرط دوم (شریط 650+)                            |
| M5        | تغییر && به || در تابع evaluateRequest در شرط دوم                |
| M6        | تغییر `<` به `<=` در شرط `creditScore < 600` در evaluateRequest  |
| M7        | تغییر `loanAmount <= 50000` به `loanAmount < 50000`               |
| M8        | حذف دستور `return false` در انتهای تابع isEligible                |
| M9        | تغییر شرط‌های اعتبارسنجی در isInputValid از `>=` و `<=` به `>` و `<`|
| M10       | تغییر شرط `employmentStable` به `!employmentStable`              |
| M11       | تغییر شرط `hasGuarantor` به `!hasGuarantor` in isEligible         |

---

### ورودی و خروجی اصلی (بدون جهش):

| Test Case | creditScore | income | loanAmount | hasGuarantor | repaymentMonths | employmentStable | evaluateRequest (خروجی) |
|-----------|-------------|--------|------------|--------------|-----------------|------------------|--------------------------|
| TC1       | 800         | 55000  | 45000      | true         | 2               | true             | APPROVED                 |
| TC2       | 550         | 55000  | 45000      | true         | 2               | true             | PENDING                  |
| TC3       | 550         | 55000  | 45000      | false        | 2               | false            | REJECTED_LOW_CREDIT      |

---

| جهش   | توضیح جهش                           | TC1 خروجی جهش متفاوت؟ | TC2 خروجی جهش متفاوت؟ | TC3 خروجی جهش متفاوت؟ | جهش کشته شده؟ |
|--------|---------------------------------|----------------------|----------------------|----------------------|--------------|
| M1     | `creditScore >= 700` → `creditScore > 700` | خیر (TC1: APPROVED، شرط 700 در TC1 پاس شده) | خیر (TC2: تست شرط 700 نیست) | خیر (TC3: تست شرط 700 نیست) | ✘ نه          |
| M2     | `&&` → `||` در شرط `creditScore >= 650 && income >= 60000 ...` | بله (TC1: APPROVED اصلی؛ با || ممکن تغییر خروجی رخ دهد) | بله (TC2: خروجی قطعا تغییر می‌کند) | بله (TC3: ممکن متفاوت) | ✔ بله         |
| M3     | معکوس کردن مقدار برگشتی `isInputValid` از false به true | بله (TC1: TC1 ورودی‌ها معتبر است، مقدار برگشتی برعکس می شود) | بله (TC2: ورودی‌ها نامعتبر (repaymentMonths=0)، ورودی الآن معتبر می‌شود) | بله (TC3: similar) | ✔ بله         |
| M4     | حذف شرط income از شرط دوم (که 650+ است) | بله (این شرط حساسی است، حذفش باعث تغییر نتیجه می‌شود) | بله | بله | ✔ بله |
| M5     | تغییر && به || در شرط دوم در `evaluateRequest` (ردیف else if) | بله (TC3 و TC2، تغییر خروجی در REJECTED_LOW_CREDIT) | بله | بله | ✔ بله |
| M6     | تغییر `< 600` به `<= 600` در check رد کردن | بله (TC2 و 3 خروجی تغییر می‌کند، 550 < 600, اما 600 = تغییر دارد) | بله | بله | ✔ بله |
| M7     | تغییر `loanAmount <= 50000` به `< 50000` | بله (TC1 loanAmount=45000 < 50000، تفاوت برای مقدار مرزی 50000 نیست اما ممکن در شرایط خاص باشد) | خیر | خیر | ✘ نه          |
| M8     | حذف `return false` نهایی در isEligible | بله (ممکن است خروجی پیش‌فرض برخلاف قبل باشد) | بله | بله | ✔ بله |
| M9     | تغییر شرط اعتبارسنجی `>=` و `<=` به `>` و `<` | بله (TC1 که ورودی در مرز است ممکن متفاوت شود) | بله | بله | ✔ بله |
| M10    | تغییر شرط `employmentStable` به `!employmentStable` | بله (TC1 شرایط `employmentStable=true` که تغییر می‌کند) | خیر | خیر | ✔ بله |
| M11    | تغییر `hasGuarantor` به `!hasGuarantor` | بله (TC3 که hasGuarantor=false) | بله | بله | ✔ بله |

---

## جدول نتایج نهایی

| جهش   | کشته شد (Killed) | دلیل خلاصه کشته شدن                                                                          |
|--------|-----------------|----------------------------------------------------------------------------------------------|
| M1     | خیر              | شرط برابری تفاوتی در تست‌های داده شده ایجاد نکرد                                            |
| M2     | بله              | جایگزینی && به || باعث تغییر شرایط شرط‌ها و خروجی‌ها شد                                    |
| M3     | بله              | معکوس سازی مقدار ورودی معتبر باعث تغییر خروجی شد                                           |
| M4     | بله              | حذف شرط مهم باعث تغییر نتیجه شد                                                           |
| M5     | بله              | تغییر شرط در evaluateRequest در شرط رد کردن                                              |
| M6     | بله              | تغییر بازه نامعتبر باعث تغییر خروجی برای TC های مرز                                     |
| M7     | خیر              | به دلیل ورودی‌های داده شده تاثیری در خروجی نداشت                                         |
| M8     | بله              | حذف return false نهایی منجر به تغییر مقدار برگشتی شد                                   |
| M9     | بله              | تغییر شرط اعتبارسنجی ورودی باعث تغییر در وضعیت‌ خروجی تست‌های مرزی                       |
| M10    | بله              | معکوس شرط employmentStable منجر به تغییر وضعیت واقعی کد شد                               |
| M11    | بله              | معکوس کردن شرط hasGuarantor تغییر واضح در منطق برنامه ایجاد کرد                           |

---

### Mutation Score

| تعداد کل جهش‌ها | تعداد جهش‌های کشته شده | Mutation Score |
|-----------------|-----------------------|---------------:|
| 11              | 9                     | 81.8%          |

## Section C

The mutations and related test are documented here.
