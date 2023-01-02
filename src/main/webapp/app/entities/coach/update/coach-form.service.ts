import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICoach, NewCoach } from '../coach.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICoach for edit and NewCoachFormGroupInput for create.
 */
type CoachFormGroupInput = ICoach | PartialWithRequiredKeyOf<NewCoach>;

type CoachFormDefaults = Pick<NewCoach, 'id'>;

type CoachFormGroupContent = {
  id: FormControl<ICoach['id'] | NewCoach['id']>;
  country: FormControl<ICoach['country']>;
  name: FormControl<ICoach['name']>;
  surname: FormControl<ICoach['surname']>;
  type: FormControl<ICoach['type']>;
  email: FormControl<ICoach['email']>;
  phone: FormControl<ICoach['phone']>;
  resume: FormControl<ICoach['resume']>;
  contact: FormControl<ICoach['contact']>;
  lang: FormControl<ICoach['lang']>;
};

export type CoachFormGroup = FormGroup<CoachFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CoachFormService {
  createCoachFormGroup(coach: CoachFormGroupInput = { id: null }): CoachFormGroup {
    const coachRawValue = {
      ...this.getFormDefaults(),
      ...coach,
    };
    return new FormGroup<CoachFormGroupContent>({
      id: new FormControl(
        { value: coachRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      country: new FormControl(coachRawValue.country, {
        validators: [Validators.required],
      }),
      name: new FormControl(coachRawValue.name, {
        validators: [Validators.required],
      }),
      surname: new FormControl(coachRawValue.surname, {
        validators: [Validators.required],
      }),
      type: new FormControl(coachRawValue.type, {
        validators: [Validators.required],
      }),
      email: new FormControl(coachRawValue.email),
      phone: new FormControl(coachRawValue.phone),
      resume: new FormControl(coachRawValue.resume),
      contact: new FormControl(coachRawValue.contact),
      lang: new FormControl(coachRawValue.lang),
    });
  }

  getCoach(form: CoachFormGroup): ICoach | NewCoach {
    return form.getRawValue() as ICoach | NewCoach;
  }

  resetForm(form: CoachFormGroup, coach: CoachFormGroupInput): void {
    const coachRawValue = { ...this.getFormDefaults(), ...coach };
    form.reset(
      {
        ...coachRawValue,
        id: { value: coachRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CoachFormDefaults {
    return {
      id: null,
    };
  }
}
