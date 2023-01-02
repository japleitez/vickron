import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPerson, NewPerson } from '../person.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPerson for edit and NewPersonFormGroupInput for create.
 */
type PersonFormGroupInput = IPerson | PartialWithRequiredKeyOf<NewPerson>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IPerson | NewPerson> = Omit<T, 'start' | 'end'> & {
  start?: string | null;
  end?: string | null;
};

type PersonFormRawValue = FormValueOf<IPerson>;

type NewPersonFormRawValue = FormValueOf<NewPerson>;

type PersonFormDefaults = Pick<NewPerson, 'id' | 'start' | 'end'>;

type PersonFormGroupContent = {
  id: FormControl<PersonFormRawValue['id'] | NewPerson['id']>;
  name: FormControl<PersonFormRawValue['name']>;
  surname: FormControl<PersonFormRawValue['surname']>;
  email: FormControl<PersonFormRawValue['email']>;
  license: FormControl<PersonFormRawValue['license']>;
  start: FormControl<PersonFormRawValue['start']>;
  end: FormControl<PersonFormRawValue['end']>;
};

export type PersonFormGroup = FormGroup<PersonFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PersonFormService {
  createPersonFormGroup(person: PersonFormGroupInput = { id: null }): PersonFormGroup {
    const personRawValue = this.convertPersonToPersonRawValue({
      ...this.getFormDefaults(),
      ...person,
    });
    return new FormGroup<PersonFormGroupContent>({
      id: new FormControl(
        { value: personRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      name: new FormControl(personRawValue.name, {
        validators: [Validators.required],
      }),
      surname: new FormControl(personRawValue.surname, {
        validators: [Validators.required],
      }),
      email: new FormControl(personRawValue.email, {
        validators: [Validators.required],
      }),
      license: new FormControl(personRawValue.license),
      start: new FormControl(personRawValue.start),
      end: new FormControl(personRawValue.end),
    });
  }

  getPerson(form: PersonFormGroup): IPerson | NewPerson {
    return this.convertPersonRawValueToPerson(form.getRawValue() as PersonFormRawValue | NewPersonFormRawValue);
  }

  resetForm(form: PersonFormGroup, person: PersonFormGroupInput): void {
    const personRawValue = this.convertPersonToPersonRawValue({ ...this.getFormDefaults(), ...person });
    form.reset(
      {
        ...personRawValue,
        id: { value: personRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PersonFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      start: currentTime,
      end: currentTime,
    };
  }

  private convertPersonRawValueToPerson(rawPerson: PersonFormRawValue | NewPersonFormRawValue): IPerson | NewPerson {
    return {
      ...rawPerson,
      start: dayjs(rawPerson.start, DATE_TIME_FORMAT),
      end: dayjs(rawPerson.end, DATE_TIME_FORMAT),
    };
  }

  private convertPersonToPersonRawValue(
    person: IPerson | (Partial<NewPerson> & PersonFormDefaults)
  ): PersonFormRawValue | PartialWithRequiredKeyOf<NewPersonFormRawValue> {
    return {
      ...person,
      start: person.start ? person.start.format(DATE_TIME_FORMAT) : undefined,
      end: person.end ? person.end.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
