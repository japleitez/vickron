import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ICompany, NewCompany } from '../company.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICompany for edit and NewCompanyFormGroupInput for create.
 */
type CompanyFormGroupInput = ICompany | PartialWithRequiredKeyOf<NewCompany>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ICompany | NewCompany> = Omit<T, 'start' | 'end'> & {
  start?: string | null;
  end?: string | null;
};

type CompanyFormRawValue = FormValueOf<ICompany>;

type NewCompanyFormRawValue = FormValueOf<NewCompany>;

type CompanyFormDefaults = Pick<NewCompany, 'id' | 'start' | 'end'>;

type CompanyFormGroupContent = {
  id: FormControl<CompanyFormRawValue['id'] | NewCompany['id']>;
  country: FormControl<CompanyFormRawValue['country']>;
  name: FormControl<CompanyFormRawValue['name']>;
  state: FormControl<CompanyFormRawValue['state']>;
  license: FormControl<CompanyFormRawValue['license']>;
  start: FormControl<CompanyFormRawValue['start']>;
  end: FormControl<CompanyFormRawValue['end']>;
  email: FormControl<CompanyFormRawValue['email']>;
  phone: FormControl<CompanyFormRawValue['phone']>;
  address: FormControl<CompanyFormRawValue['address']>;
  billingPhone: FormControl<CompanyFormRawValue['billingPhone']>;
  billingEmail: FormControl<CompanyFormRawValue['billingEmail']>;
  billingAddress: FormControl<CompanyFormRawValue['billingAddress']>;
  vatin: FormControl<CompanyFormRawValue['vatin']>;
};

export type CompanyFormGroup = FormGroup<CompanyFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CompanyFormService {
  createCompanyFormGroup(company: CompanyFormGroupInput = { id: null }): CompanyFormGroup {
    const companyRawValue = this.convertCompanyToCompanyRawValue({
      ...this.getFormDefaults(),
      ...company,
    });
    return new FormGroup<CompanyFormGroupContent>({
      id: new FormControl(
        { value: companyRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      country: new FormControl(companyRawValue.country, {
        validators: [Validators.required],
      }),
      name: new FormControl(companyRawValue.name, {
        validators: [Validators.required],
      }),
      state: new FormControl(companyRawValue.state, {
        validators: [Validators.required],
      }),
      license: new FormControl(companyRawValue.license),
      start: new FormControl(companyRawValue.start),
      end: new FormControl(companyRawValue.end),
      email: new FormControl(companyRawValue.email),
      phone: new FormControl(companyRawValue.phone),
      address: new FormControl(companyRawValue.address),
      billingPhone: new FormControl(companyRawValue.billingPhone),
      billingEmail: new FormControl(companyRawValue.billingEmail),
      billingAddress: new FormControl(companyRawValue.billingAddress),
      vatin: new FormControl(companyRawValue.vatin),
    });
  }

  getCompany(form: CompanyFormGroup): ICompany | NewCompany {
    return this.convertCompanyRawValueToCompany(form.getRawValue() as CompanyFormRawValue | NewCompanyFormRawValue);
  }

  resetForm(form: CompanyFormGroup, company: CompanyFormGroupInput): void {
    const companyRawValue = this.convertCompanyToCompanyRawValue({ ...this.getFormDefaults(), ...company });
    form.reset(
      {
        ...companyRawValue,
        id: { value: companyRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CompanyFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      start: currentTime,
      end: currentTime,
    };
  }

  private convertCompanyRawValueToCompany(rawCompany: CompanyFormRawValue | NewCompanyFormRawValue): ICompany | NewCompany {
    return {
      ...rawCompany,
      start: dayjs(rawCompany.start, DATE_TIME_FORMAT),
      end: dayjs(rawCompany.end, DATE_TIME_FORMAT),
    };
  }

  private convertCompanyToCompanyRawValue(
    company: ICompany | (Partial<NewCompany> & CompanyFormDefaults)
  ): CompanyFormRawValue | PartialWithRequiredKeyOf<NewCompanyFormRawValue> {
    return {
      ...company,
      start: company.start ? company.start.format(DATE_TIME_FORMAT) : undefined,
      end: company.end ? company.end.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
